package com.example.movienight.screen.selectMovie

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import android.widget.TextView
import androidx.core.view.isVisible
import com.asynctaskcoffee.cardstack.CardContainer
import com.asynctaskcoffee.cardstack.CardListener
import com.example.core.model.Content
import com.example.movienight.Screen
import com.example.movienight.components.AppLoadingView
import com.example.movienight.exctation.dpToPx
import com.example.movienight.screen.selectMovie.cardContaioner.CardContainerAdapter


@SuppressLint("ViewConstructor")
class SelectContentView(
    context: Context,
    onSwipeCompleted: (List<Content>) -> Unit,
    onContentClickListener: (Content) -> Unit
) : FrameLayout(context) {

    private var theList = listOf<Content>()
    private val favoriteList = mutableListOf<Content>()

    private val cardContainer = CardContainer(context, null).apply {
        isVisible = false
        margin = 30.dpToPx

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
    private val contentView = LinearLayout(context).apply {
        orientation = VERTICAL
        isVisible = false
    }
    private val loadingView = AppLoadingView(context)

    private val adapter = CardContainerAdapter(context, onContentClickListener)

    init {
        contentView.apply {
            val cardContainerLayoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            ).apply {
                gravity = Gravity.CENTER
                setPadding(5.dpToPx, 5.dpToPx, 5.dpToPx, 5.dpToPx)
            }
            cardContainer.addHeaderView(actionBar)
            addView(
                cardContainer,
                cardContainerLayoutParams
            )
            cardContainer.setAdapter(adapter)
        }
        addView(contentView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
        addView(loadingView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    fun submitList(list: List<Content>) {
        theList = list
        adapter.submitList(list)
        cardContainer.notifyAppendData()
        loadingView.isVisible = false
        cardContainer.isVisible = true
        contentView.isVisible = true
        contentView.bringToFront()
    }

    fun loading() {
        cardContainer.isVisible = false
        contentView.isVisible = false
        loadingView.isVisible = true
        loadingView.bringToFront()
    }
}