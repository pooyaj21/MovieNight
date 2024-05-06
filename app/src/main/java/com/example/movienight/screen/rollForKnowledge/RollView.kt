package com.example.movienight.screen.rollForKnowledge

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.example.core.model.Movie
import com.example.movienight.exctation.dpToPx
import com.example.movienight.screen.rollForKnowledge.component.WheelView
import kotlin.random.Random

class RollView(context: Context) : LinearLayout(context) {
    private val titleTextView = TextView(context).apply {
        text = "Your fate will be chosen now!!"
        setTextColor(Color.WHITE)
        textSize = 8.dpToPx.toFloat()
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val nameTextView = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
        setTextColor(Color.WHITE)
        textSize = 10.dpToPx.toFloat()
    }

    init {
        orientation = VERTICAL
        addView(titleTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(nameTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    fun success(firstName: String?, secondName: String?, list: List<Movie>?) {
        nameTextView.text = "${firstName ?: ""}\n&\n${secondName ?: ""}"
        val size = list?.size ?: 0
        val theChosenOne = Random.nextInt(size)
        Log.d(TAG, "The chosen one is:${list?.get(theChosenOne) ?: ""}")
        val sliceDegree = 360 / size
        val theChosenOneMidDegree = (sliceDegree * (theChosenOne)) + (sliceDegree / 2)
        val wheelView = WheelView(context, list ?: listOf())
        wheelView.spinArrow(theChosenOneMidDegree.toFloat())
        addView(wheelView)
    }
}