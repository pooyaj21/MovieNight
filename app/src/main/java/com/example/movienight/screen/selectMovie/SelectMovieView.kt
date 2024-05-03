package com.example.movienight.screen.selectMovie

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.asynctaskcoffee.cardstack.CardContainer
import com.example.core.model.Movie
import com.example.movienight.Screen
import com.example.movienight.exctation.dpToPx
import com.example.movienight.screen.selectMovie.cardContaioner.CardContainerAdapter


class SelectMovieView(context: Context) : LinearLayout(context) {
    private val cardContainer = CardContainer(context, null).apply {
        isVisible = false
    }

    private val imDownTextView = TextView(context).apply {
        text = "<- I'm Down"
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
    }
    private val notFeelingItTextView = TextView(context).apply {
        text = "Not Feeling It ->"
        gravity = Gravity.CENTER

        setTextColor(Color.WHITE)
    }
    private val actionBar = LinearLayout(context).apply {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        addView(imDownTextView, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
        addView(
            notFeelingItTextView,
            LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        )
    }

    private val adapter = CardContainerAdapter(context)

    init {
        orientation = VERTICAL
        val actionBarLayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            (Screen.size.height * 0.2).toInt()
        ).apply {
            gravity = Gravity.CENTER
            setPadding(5.dpToPx, 5.dpToPx, 5.dpToPx, 5.dpToPx)
        }
        val cardContainerLayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            (Screen.size.height * 0.7).toInt()
        ).apply {
            gravity = Gravity.CENTER
            setPadding(5.dpToPx, 5.dpToPx, 5.dpToPx, 5.dpToPx)
        }

        addView(
            actionBar,
            actionBarLayoutParams
        )
        addView(
            cardContainer,
            cardContainerLayoutParams
        )

        cardContainer.setAdapter(adapter)
    }

    fun submitList(list: List<Movie>) {
        adapter.submitList(list)
        cardContainer.notifyAppendData()
        cardContainer.isVisible = true
    }
}