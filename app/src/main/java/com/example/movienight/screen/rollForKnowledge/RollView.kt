package com.example.movienight.screen.rollForKnowledge

import android.annotation.SuppressLint
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

@SuppressLint("ViewConstructor")
class RollView(
    context: Context,
    private val onMovieSelect: (Movie) -> Unit
) : LinearLayout(context) {
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
        addView(titleTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(0,20.dpToPx,0,0)
        })
        addView(nameTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    fun success(firstName: String?, secondName: String?, list: List<Movie>?) {
        if (firstName!=null && secondName!=null) {
            nameTextView.text = "${firstName}\n&\n${secondName}"
        }else nameTextView.text = "Your"

        list?.let {
            val size = list.size
            val theChosenOneNumber = Random.nextInt(size)
            val theChosenOneMovie = list[theChosenOneNumber]
            Log.d(TAG, "The chosen one is:${theChosenOneMovie}")
            val sliceDegree = 360 / size
            val theChosenOneMidDegree = (sliceDegree * (theChosenOneNumber)) + (sliceDegree / 2)
            val wheelView = WheelView(context, list)
            wheelView.spinArrow(theChosenOneMidDegree.toFloat()) {
                onMovieSelect(theChosenOneMovie)
            }
            addView(wheelView)
        }
    }
}