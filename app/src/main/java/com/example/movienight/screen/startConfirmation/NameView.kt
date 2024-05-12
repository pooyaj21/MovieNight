package com.example.movienight.screen.startConfirmation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.movienight.R
import com.example.movienight.exctation.dpToPx

class NameView(
    context: Context,
    goNext: OnClickListener
) : FrameLayout(context) {

    private val titleTextView = TextView(context).apply {
        text = "Are you ready to choose your fate?"
        textAlignment = TEXT_ALIGNMENT_CENTER
        setTextColor(Color.WHITE)
    }
    private val nameTextView = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
        textSize = 10.dpToPx.toFloat()
        setTextColor(Color.WHITE)
    }
    private val goNextButton = Button(context).apply {
        text = "Let's Go"
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(resources.getColor(R.color.mahogany, resources.newTheme()))
            setStroke(1.dpToPx, Color.WHITE)
            cornerRadius = 12.dpToPx.toFloat()
        }
        setOnClickListener(goNext)
    }

    init {
        val contentView = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            addView(
                titleTextView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0,0,0,5.dpToPx)
                }
            )
            addView(
                nameTextView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    setMargins(0,0,0,10.dpToPx)
                }
            )
            addView(
                goNextButton,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            )
        }
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
            }
        addView(contentView, layoutParams)
    }

    fun setName(name: String) {
        nameTextView.text = name
    }
}