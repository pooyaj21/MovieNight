package com.example.movienight.screen.selectMovie.cardContaioner

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.core.model.Movie
import com.example.movienight.R
import com.example.movienight.exctation.dpToPx
import com.example.movienight.exctation.load

@SuppressLint("ViewConstructor")
class MovieCardView(movie: Movie, context: Context) : CardView(context) {
    private val image = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER
        load(movie.posterImage, R.drawable.place_holder)
    }
    private val title = TextView(context).apply {
        text = movie.title
        textSize = 5.dpToPx.toFloat()
        setTextColor(Color.WHITE)
    }

    init {
        val frameLayout = FrameLayout(context).apply {
            val imageLayoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            val titleLayoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.BOTTOM
                    setMargins(5.dpToPx, 0, 5.dpToPx, 10.dpToPx)
                }
            addView(image, imageLayoutParams)
            addView(title, titleLayoutParams)
        }
        addView(frameLayout, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }
}