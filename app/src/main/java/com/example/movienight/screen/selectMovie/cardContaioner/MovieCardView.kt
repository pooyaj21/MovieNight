package com.example.movienight.screen.selectMovie.cardContaioner

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.core.model.Movie
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.exctation.dpToPx
import com.example.movienight.exctation.load

@SuppressLint("ViewConstructor")
class MovieCardView(movie: Movie, context: Context) : CardView(context) {
    private val image = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER
        load(movie.posterImage, R.drawable.place_holder)
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
    }


    init {
        setBackgroundColor(resources.getColor(R.color.gray))
        val contentView = LinearLayout(context).apply {
            val cardHeight = (Screen.size.height * 0.8).toInt()
            orientation = LinearLayout.VERTICAL
            val imageLayoutParams =
                LayoutParams((Screen.size.width * 0.9).toInt(), (cardHeight * 0.7).toInt()).apply {
                    gravity = Gravity.CENTER
                }
            val titleLayoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            val descriptionLayoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, (cardHeight * 0.15).toInt())
            addView(image, imageLayoutParams)
            addView(name, titleLayoutParams)
            addView(description, descriptionLayoutParams)
        }
        addView(contentView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }
}