package com.example.movienight.screen.movieDetail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.exctation.dpToPx
import com.example.movienight.exctation.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily


class MovieDetailCellView(context: Context) : LinearLayout(context) {
    private val imageView = ShapeableImageView(context).apply {
        shapeAppearanceModel = shapeAppearanceModel
            .toBuilder()
            .setTopLeftCorner(CornerFamily.ROUNDED, radius.toFloat())
            .setTopRightCorner(CornerFamily.ROUNDED, radius.toFloat())
            .setBottomLeftCorner(CornerFamily.ROUNDED, radius.toFloat())
            .setBottomRightCorner(CornerFamily.ROUNDED, radius.toFloat())
            .build()
        elevation = 7.dpToPx.toFloat()
        gravity = Gravity.CENTER
    }
    private val titleTextView = TextView(context).apply {
        textSize = 7.dpToPx.toFloat()
    }
    private val descriptionTitleTextView = TextView(context).apply {
        text = "Description:"
        textSize = 5.dpToPx.toFloat()
        setTextColor(Color.WHITE)
    }
    private val descriptionTextView = TextView(context).apply {
        setTextColor(Color.WHITE)
    }
    private val descriptionView = LinearLayout(context).apply {
        orientation = VERTICAL
        addView(
            descriptionTitleTextView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        )
        addView(
            descriptionTextView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        )
    }
    private val genresView = GenresView(context)
    private val ratingBar = RatingBar(context).apply {
        numStars = 5
        setIsIndicator(true)
    }

    init {
        orientation = VERTICAL
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.GRAY)
            cornerRadius = radius.toFloat()
        }

        addView(
            imageView,
            LayoutParams((Screen.size.height / 4), (Screen.size.height / 4)).apply {
                setMargins(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, 0)
            }
        )
        addView(
            titleTextView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, 0)
            }
        )
        addView(
            descriptionView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, 0)
            }
        )
        addView(
            genresView,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, 0)
            }
        )
        addView(
            ratingBar,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(MAIN_MARGIN, MAIN_MARGIN, MAIN_MARGIN, 0)
            }
        )
    }

    fun success(uiMovie: UiMovie) {
        imageView.load(uiMovie.backdropImage, R.drawable.place_holder)
        titleTextView.text = uiMovie.title
        descriptionTextView.text = uiMovie.overview
        genresView.bindGenres(uiMovie.genres)
        ratingBar.rating = (uiMovie.voteAverage / 2).toFloat()
    }

    companion object {
        private val MAIN_MARGIN = 5.dpToPx
        private val radius = 16.dpToPx
    }
}