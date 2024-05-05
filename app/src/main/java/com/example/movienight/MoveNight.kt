package com.example.movienight

import android.app.Application
import android.util.Log
import com.example.movienight.architect.ActivityLifeCycleLogger
import com.example.movienight.architect.FragmentLifecycleLogger
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.io.File

class MoveNight : Application() {

    override fun onCreate() {
        super.onCreate()
        // Setup RxJava
        RxJavaPlugins.setErrorHandler {
            Log.d("Care", it.localizedMessage ?: "Unknown RXError")
        }
        // Setup Koin
        startKoin {
            androidContext(this@MoveNight)
            modules(appKoinModule)
        }
        // Setup Lifecycle Logger
        registerActivityLifecycleCallbacks(ActivityLifeCycleLogger())
        registerActivityLifecycleCallbacks(FragmentLifecycleLogger.Build())

        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
    }

}