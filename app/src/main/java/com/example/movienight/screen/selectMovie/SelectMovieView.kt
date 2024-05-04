package com.example.movienight.screen.selectMovie

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.asynctaskcoffee.cardstack.CardContainer
import com.asynctaskcoffee.cardstack.CardListener
import com.asynctaskcoffee.cardstack.px
import com.example.core.model.Movie
import com.example.movienight.Screen
import com.example.movienight.exctation.dpToPx
import com.example.movienight.screen.selectMovie.cardContaioner.CardContainerAdapter


@SuppressLint("ViewConstructor")
class SelectMovieView(
    context: Context,
    onSwipeCompleted: (List<Movie>) -> Unit
) : LinearLayout(context) {

    private var theList = listOf<Movie>()
    private val favoriteList = mutableListOf<Movie>()

    private val cardContainer = CardContainer(context, null).apply {
        isVisible = false
        margin = 30.px
        maxStackSize = 6

        setOnCardActionListener(object : CardListener {
            override fun onItemShow(position: Int, model: Any) {}

            override fun onLeftSwipe(position: Int, model: Any) {}

            override fun onRightSwipe(position: Int, model: Any) {
                favoriteList.add(theList[position])
                Log.d(ContentValues.TAG, "Movie added to favorite: ${theList[position]}")
            }

            override fun onSwipeCancel(position: Int, model: Any) {}

            override fun onSwipeCompleted() {
                onSwipeCompleted(favoriteList)
            }

        })
    }
    private val notFeelingItTextView = TextView(context).apply {
        text = "<- Not Feeling It"
        gravity = Gravity.START

        setTextColor(Color.WHITE)
    }
    private val imDownTextView = TextView(context).apply {
        text = "I'm Down ->"
        gravity = Gravity.END
        setTextColor(Color.WHITE)
    }
    private val actionBar = LinearLayout(context).apply {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        addView(
            notFeelingItTextView,
            LayoutParams((Screen.size.width * 0.4).toInt(), LayoutParams.WRAP_CONTENT)
        )
        addView(
            imDownTextView,
            LayoutParams((Screen.size.width * 0.4).toInt(), LayoutParams.WRAP_CONTENT)
        )
    }

    private val adapter = CardContainerAdapter(context)

    init {
        orientation = VERTICAL
        val actionBarLayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            (Screen.size.height * 0.1).toInt()
        ).apply {
            gravity = Gravity.CENTER
            setPadding(5.dpToPx, 5.dpToPx, 5.dpToPx, 5.dpToPx)
        }
        val cardContainerLayoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            (Screen.size.height * 0.8).toInt()
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
        theList = list
        adapter.submitList(list)
        cardContainer.notifyAppendData()
        cardContainer.isVisible = true
    }
}