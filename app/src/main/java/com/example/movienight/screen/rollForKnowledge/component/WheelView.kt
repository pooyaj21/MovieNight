package com.example.movienight.screen.rollForKnowledge.component

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.animation.doOnEnd
import com.example.core.model.Content
import com.example.movienight.R
import com.example.movienight.Screen

@SuppressLint("ViewConstructor")
class WheelView(context: Context, list: List<Content>) : ConstraintLayout(context) {
    private val circleView = CircleView(context, list).apply {
        id = generateViewId()
    }
    private val arrowView = ImageView(context).apply {
        id = generateViewId()
        setImageResource(R.drawable.arrow_tr)
    }

    init {

        addView(circleView)
        addView(
            arrowView,
            LayoutParams(
                (Screen.size.height * 0.1).toInt(),
                (Screen.size.height * 0.1).toInt()
            )
        )

        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        // ArrowView
        constraintSet.connect(
            circleView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            0
        )
        constraintSet.connect(
            circleView.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END,
            0
        )
        constraintSet.connect(
            circleView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            circleView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            0
        )

        // ArrowView
        constraintSet.connect(
            arrowView.id,
            ConstraintSet.START,
            ConstraintSet.PARENT_ID,
            ConstraintSet.START,
            0
        )
        constraintSet.connect(
            arrowView.id,
            ConstraintSet.END,
            ConstraintSet.PARENT_ID,
            ConstraintSet.END,
            0
        )
        constraintSet.connect(
            arrowView.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            arrowView.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM,
            0
        )

        // Apply constraints
        constraintSet.applyTo(this)
    }

    fun spinArrow(desiredPosition: Float, onRollComplete: () -> Unit) {
        val fullCircle = 360f
        val totalSpins = 8
        val targetRotation = fullCircle * totalSpins
        val durationMillis = 3000L

        // Create an ObjectAnimator to animate the rotation of the bottle
        val stopRotation = (360 * (totalSpins - 1)) + desiredPosition

        ObjectAnimator.ofFloat(this.arrowView, "rotation", 0f, targetRotation).apply {
            duration = durationMillis
            interpolator =
                LinearInterpolator() // Ensure constant speed throughout the animation
        }.apply {
            addUpdateListener { animation ->
                val currentRotation = animation.animatedValue as Float

                // Check if the bottle rotation exceeds or reaches the desired position
                if (currentRotation >= stopRotation) {
                    // Stop the spinning animation
                    cancel()
                }
            }
            doOnEnd {
                onRollComplete.invoke()
            }
        }.start()
    }
}