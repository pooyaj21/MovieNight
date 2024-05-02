package com.example.movienight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var mainView: MainView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = MainView(activity = this)
        setContentView(mainView)
        Screen.updateSize(this, resources.configuration)
    }

    override fun onDestroy() {
        mainView = null
        super.onDestroy()
    }
}