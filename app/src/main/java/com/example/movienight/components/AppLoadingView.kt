package com.example.movienight.components

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.example.movienight.R
import com.google.android.material.progressindicator.CircularProgressIndicator

class AppLoadingView(context: Context) : FrameLayout(context) {

    private val loadingView = CircularProgressIndicator(context).apply {
        isIndeterminate = true
        setIndicatorColor(resources.getColor(R.color.mahogany))
    }

    init {
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.CENTER
            }
        addView(loadingView, layoutParams)
    }

}