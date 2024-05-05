package com.example.movienight.architect


import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

private interface LifeCycleLogger {
    fun log(entity: Any, lifecycle: String) {
        val entityName = entity::class.simpleName
        Log.d(entityName,"$entityName (@${System.identityHashCode(entity)}), lifecycle: $lifecycle")
    }
}

class FragmentLifecycleLogger private constructor(): FragmentManager.FragmentLifecycleCallbacks(),
    LifeCycleLogger {

    override fun onFragmentAttached(fm: FragmentManager, fragment: Fragment, context: Context) {
        val lifeCycle = if (fragment is Fragment) {
            "onAttached() -> source: ${fragment.arguments}"
        } else {
            "onAttached()"
        }
        log(fragment, lifeCycle)
    }

    override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
        log(fragment, "onCreated")
    }

    override fun onFragmentViewCreated(
        fm: FragmentManager,
        fragment: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) {
        val lifeCycle = if (fragment is Fragment) {
            "onViewCreated() -> shouldCacheView: ${fragment}"
        } else {
            "onViewCreated()"
        }
        log(fragment, lifeCycle)
    }

    override fun onFragmentStarted(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onStarted")
    }

    override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onResumed")
    }

    override fun onFragmentPaused(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onPaused")
    }

    override fun onFragmentStopped(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onStopped")
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onViewDestroyed")
    }

    override fun onFragmentDestroyed(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onDestroyed")
    }

    override fun onFragmentDetached(fm: FragmentManager, fragment: Fragment) {
        log(fragment,"onDetached")
    }

    class Build : Application.ActivityLifecycleCallbacks {

        private val logger = FragmentLifecycleLogger()

        private val Activity.supportFragmentManager: FragmentManager?
            get() = (this as? FragmentActivity)?.supportFragmentManager

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            activity.supportFragmentManager?.registerFragmentLifecycleCallbacks(logger, true)
        }

        override fun onActivityDestroyed(activity: Activity) {
            activity.supportFragmentManager?.unregisterFragmentLifecycleCallbacks(logger)
        }

        override fun onActivityStarted(activity: Activity) {
            // Nothing
        }
        override fun onActivityResumed(activity: Activity) {
            // Nothing
        }
        override fun onActivityPaused(activity: Activity) {
            // Nothing
        }
        override fun onActivityStopped(activity: Activity) {
            // Nothing
        }
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // Nothing
        }
    }


}

class ActivityLifeCycleLogger : Application.ActivityLifecycleCallbacks, LifeCycleLogger {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        log(activity, "onCreate savedInstanceState:$savedInstanceState")
    }

    override fun onActivityStarted(activity: Activity) {
        log(activity, "onStart")
    }

    override fun onActivityResumed(activity: Activity) {
        log(activity, "onResume")
    }

    override fun onActivityPaused(activity: Activity) {
        log(activity, "onPause")
    }

    override fun onActivityStopped(activity: Activity) {
        log(activity, "onStop")
    }

    override fun onActivityDestroyed(activity: Activity) {
        log(activity, "onDestroy")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        log(activity, "onSaveInstanceState")
    }

}