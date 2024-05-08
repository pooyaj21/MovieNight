package com.example.movienight.screen.movieDetail

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import com.example.core.model.Genre
import com.example.movienight.exctation.dpToPx
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.JustifyContent

class GenresView(context: Context) : FlexboxLayout(context) {

    init {
        justifyContent = JustifyContent.FLEX_START
        alignItems = AlignItems.CENTER
        flexWrap = FlexWrap.WRAP
    }

    private fun addGenre(genre: Genre) {
        val cellView = TextView(context).apply {
            text = genre.name
            setTextColor(Color.WHITE)
            setPadding(margin, margin / 2, margin, margin / 2)
            background = GradientDrawable().apply {
                shape = GradientDrawable.RECTANGLE
                setColor(Color.GRAY)
                setStroke(1.dpToPx, Color.WHITE)
                cornerRadius = 12.dpToPx.toFloat()
            }
        }
        addView(
            cellView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                setMargins(4.dpToPx, 4.dpToPx, 4.dpToPx, 4.dpToPx)
            }
        )
    }

    fun bindGenres(genres: List<Genre>) {
        removeAllViews()
        genres.forEach {
            addGenre(it)
        }
    }

    companion object {
        private val margin = 16.dpToPx
    }
}