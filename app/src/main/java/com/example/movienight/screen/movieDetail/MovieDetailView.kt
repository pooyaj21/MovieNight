package com.example.movienight.screen.movieDetail

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.movienight.components.AppLoadingView
import com.example.movienight.exctation.dpToPx

class MovieDetailView(context: Context) : FrameLayout(context) {

    private val loadingView = AppLoadingView(context)
    private val movieDetailCellView = MovieDetailCellView(context)

    init {
        setBackgroundColor(Color.TRANSPARENT)
        addView(
            movieDetailCellView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
                setMargins(5.dpToPx, 0, 5.dpToPx, 0)
            }
        )
        addView(
            loadingView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                gravity = Gravity.CENTER
            })
    }

    fun loading() {
        movieDetailCellView.isVisible = false
        loadingView.isVisible = true
        loadingView.bringToFront()
    }

    fun success(uiMovie: UiMovie) {
        movieDetailCellView.success(uiMovie)
        loadingView.isVisible = false
        movieDetailCellView.isVisible = true
        movieDetailCellView.bringToFront()
    }
}