package com.example.movienight.screen.rollForKnowledge

import android.animation.ObjectAnimator
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.core.model.Movie
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.screen.rollForKnowledge.component.CircleView
import kotlin.random.Random

class RollView(context: Context) : LinearLayout(context) {
    private val titleTextView = TextView(context).apply {
        text = "Your fate will be chosen now!!"
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val nameTextView = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
    }

    init {
        orientation = VERTICAL
        addView(titleTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
        addView(nameTextView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))
    }

    fun success(firstName: String?, secondName: String?, list: List<Movie>?) {
        nameTextView.text = "${firstName ?: ""}\n&\n${secondName ?: ""}"
        val size = list?.size ?: 0
        val theChosenOne = Random.nextInt(size + 1)
        Log.d(TAG,"The chosen one is:${list?.get(theChosenOne) ?:""}")
        val sliceDegree = 360 / size
        val theChosenOneMidDegree = (sliceDegree * theChosenOne) + (sliceDegree / 2)
        val wheelView = FrameLayout(context).apply {
            val circleView = CircleView(context, list ?: listOf())
            val arrowView = ImageView(context).apply {
                setImageResource(R.drawable.arrow_tr)
                spinArrow(theChosenOneMidDegree.toFloat())
            }
            val circleLayoutParams = android.view.ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT,
                (Screen.size.height * 0.6).toInt()
            ).apply {
                gravity = Gravity.CENTER
            }
            val arrowLayoutParams = android.view.ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT,
                (Screen.size.height * 0.3).toInt()
            ).apply {
                gravity = Gravity.CENTER
            }
            addView(circleView, circleLayoutParams)
            addView(arrowView, arrowLayoutParams)

        }
        addView(
            wheelView,
            LayoutParams(LayoutParams.MATCH_PARENT, (Screen.size.height * 0.7).toInt())
        )
    }

    private fun View.spinArrow(desiredPosition: Float) {
        val fullCircle = 360f
        val totalSpins = 8
        val targetRotation = fullCircle * totalSpins
        val durationMillis = 3000L

        // Create an ObjectAnimator to animate the rotation of the bottle
        var bottleAnimator =
            ObjectAnimator.ofFloat(this, "rotation", 0f, targetRotation).apply {
                duration = durationMillis
                interpolator =
                    LinearInterpolator() // Ensure constant speed throughout the animation
            }

        // Calculate the desired position based on the number of spins
        val stopRotation = (360 * (totalSpins - 1)) + desiredPosition

        // Add an update listener to stop the animation precisely at the desired position
        bottleAnimator.addUpdateListener { animation ->
            val currentRotation = animation.animatedValue as Float

            // Check if the bottle rotation exceeds or reaches the desired position
            if (currentRotation >= stopRotation) {
                // Stop the spinning animation
                bottleAnimator.cancel()
            }
        }

        // Disable clicking on the bottle image until the animation ends

        // Start the bottle spinning animation
        bottleAnimator.start()
    }

}