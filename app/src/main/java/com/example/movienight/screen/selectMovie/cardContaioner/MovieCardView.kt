package com.example.movienight.screen.selectMovie.cardContaioner

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.core.model.Movie
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.exctation.dpToPx
import com.example.movienight.exctation.load

@SuppressLint("ViewConstructor")
class MovieCardView(
    context: Context,
    movie: Movie,
    onMovieClickListener: (Movie) -> Unit
) : LinearLayout(context) {
    private val image = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER
        load(movie.posterImage, R.drawable.place_holder)
        gravity = Gravity.CENTER
    }
    private val name = TextView(context).apply {
        text = movie.title
        textSize = 6.dpToPx.toFloat()
        setTextColor(Color.WHITE)
        setPadding(5.dpToPx, 0, 0, 0)
    }
    private val description = TextView(context).apply {
        text = movie.overview
        textSize = 4.dpToPx.toFloat()
        setTextColor(Color.WHITE)
        setPadding(5.dpToPx, 0, 0, 0)
        setOnClickListener {
            onMovieClickListener(movie)
        }
    }


    init {
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.GRAY)
            cornerRadius = radius.toFloat()
        }
        orientation = VERTICAL
        val cardHeight = (Screen.size.height * 0.8).toInt()
        val imageLayoutParams =
            LayoutParams((Screen.size.width * 0.9).toInt(), (cardHeight * 0.7).toInt())
        val titleLayoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val descriptionLayoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, (cardHeight * 0.15).toInt())
        addView(image, imageLayoutParams)
        addView(name, titleLayoutParams)
        addView(description, descriptionLayoutParams)
    }

    companion object {
        private val radius = 16.dpToPx
    }
}