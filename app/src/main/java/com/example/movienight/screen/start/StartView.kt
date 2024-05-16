package com.example.movienight.screen.start

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat.getColor
import com.example.core.model.Content
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.components.AppEditTextView
import com.example.movienight.exctation.dpToPx

@SuppressLint("ViewConstructor")
class StartView(
    context: Context,
    private val onNextClick: (firstName: String, secondName: String, type: Content.Type) -> Unit,
    private val onSoloClick: (type: Content.Type) -> Unit
) : ConstraintLayout(context) {

    private val editTextSize = Screen.size.width * 0.8

    private val firstNameEditText = AppEditTextView(context).apply {
        id = generateViewId()
        hint = "First person name"
        singleLine()
    }
    private val secondNameEditText = AppEditTextView(context).apply {
        id = generateViewId()
        hint = "Second person name"
        singleLine()
        setOnDoneClickListener {
            letsGo()
        }
    }
    private val andTextView = TextView(context).apply {
        id = generateViewId()
        setTextColor(Color.WHITE)
        text = "&"
        textSize = 25F
    }
    private val switch = SwitchCompat(context).apply {
        if (isChecked) {
            thumbDrawable.setColorFilter(
                getColor(context, R.color.mahogany),
                PorterDuff.Mode.SRC_IN
            )
            trackDrawable.setColorFilter(getColor(context, R.color.blue), PorterDuff.Mode.SRC_IN)
        } else {
            thumbDrawable.setColorFilter(getColor(context, R.color.blue), PorterDuff.Mode.SRC_IN)
            trackDrawable.setColorFilter(
                getColor(context, R.color.mahogany),
                PorterDuff.Mode.SRC_IN
            )
        }
        setOnClickListener {
            if (isChecked) {
                thumbDrawable.setColorFilter(
                    getColor(context, R.color.mahogany),
                    PorterDuff.Mode.SRC_IN
                )
                trackDrawable.setColorFilter(
                    getColor(context, R.color.blue),
                    PorterDuff.Mode.SRC_IN
                )
            } else {
                thumbDrawable.setColorFilter(
                    getColor(context, R.color.blue),
                    PorterDuff.Mode.SRC_IN
                )
                trackDrawable.setColorFilter(
                    getColor(context, R.color.mahogany),
                    PorterDuff.Mode.SRC_IN
                )
            }
        }
    }
    private val switchLayout = LinearLayout(context).apply {
        id = generateViewId()
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER

        val movieTextView = TextView(context).apply {
            setTextColor(Color.WHITE)
            text = "Movie"
            textSize = 15F
            setPadding(0,0,5.dpToPx,0)
        }
        val tvSeriesTextView = TextView(context).apply {
            setTextColor(Color.WHITE)
            text = "TV Series"
            textSize = 15F
            setPadding(5.dpToPx,0,0,0)
        }
        addView(movieTextView)
        addView(switch, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(tvSeriesTextView)
    }
    private val letsGoButton = Button(context).apply {
        id = generateViewId()
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(resources.getColor(R.color.mahogany, resources.newTheme()))
            setStroke(1.dpToPx, Color.WHITE)
            cornerRadius = 12.dpToPx.toFloat()
        }
        text = "Lets Go"
        setOnClickListener {
            letsGo()
        }
    }
    private val soloButton = Button(context).apply {
        id = generateViewId()
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(resources.getColor(R.color.blue, resources.newTheme()))
            setStroke(1.dpToPx, Color.WHITE)
            cornerRadius = 12.dpToPx.toFloat()
        }
        text = "Solo Queue"
        setOnClickListener {
            val type = if (switch.isChecked) Content.Type.TV_SERIES else Content.Type.MOVIE
            onSoloClick(type)
        }
    }


    init {
        setBackgroundColor(Color.BLACK)
        addView(firstNameEditText, LayoutParams(editTextSize.toInt(), WRAP_CONTENT))
        addView(andTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(secondNameEditText, LayoutParams(editTextSize.toInt(), WRAP_CONTENT))
        addView(switchLayout, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        addView(letsGoButton, LayoutParams((editTextSize.toInt()) / 2, WRAP_CONTENT))
        addView(soloButton, LayoutParams(((editTextSize.toInt()) / 1.5).toInt(), WRAP_CONTENT))

        val constraintSet = ConstraintSet()
        constraintSet.clone(this)

        // first name EditText
        constraintSet.connect(firstNameEditText.id, START, PARENT_ID, START, 0)
        constraintSet.connect(firstNameEditText.id, END, PARENT_ID, END, 0)
        constraintSet.connect(firstNameEditText.id, TOP, PARENT_ID, TOP, 200.dpToPx)
        // & TextView
        constraintSet.connect(andTextView.id, START, firstNameEditText.id, START, 0)
        constraintSet.connect(andTextView.id, END, firstNameEditText.id, END, 0)
        constraintSet.connect(andTextView.id, TOP, firstNameEditText.id, BOTTOM, 20.dpToPx)
        constraintSet.connect(andTextView.id, BOTTOM, secondNameEditText.id, TOP, 20.dpToPx)
        // second name EditText
        constraintSet.connect(secondNameEditText.id, START, PARENT_ID, START, 0)
        constraintSet.connect(secondNameEditText.id, END, PARENT_ID, END, 0)
        constraintSet.connect(secondNameEditText.id, BOTTOM, switchLayout.id, TOP, 10.dpToPx)
        // switch
        constraintSet.connect(switchLayout.id, START, PARENT_ID, START, 0)
        constraintSet.connect(switchLayout.id, END, PARENT_ID, END, 0)
        constraintSet.connect(switchLayout.id, TOP, secondNameEditText.id, BOTTOM, 55.dpToPx)
        // Let's Go Button
        constraintSet.connect(letsGoButton.id, START, PARENT_ID, START, 0)
        constraintSet.connect(letsGoButton.id, END, PARENT_ID, END, 0)
        constraintSet.connect(letsGoButton.id, TOP, switchLayout.id, BOTTOM, 55.dpToPx)
        // Solo Button
        constraintSet.connect(soloButton.id, START, PARENT_ID, START, 0)
        constraintSet.connect(soloButton.id, END, PARENT_ID, END, 0)
        constraintSet.connect(soloButton.id, TOP, letsGoButton.id, BOTTOM, 20.dpToPx)

        constraintSet.applyTo(this)

    }

    private fun letsGo() {
        firstNameEditText.error = null
        secondNameEditText.error = null
        val firstName = firstNameEditText.text
        val secondName = secondNameEditText.text
        val type = if (switch.isChecked) Content.Type.TV_SERIES else Content.Type.MOVIE
        if (firstName.isBlank().not() && secondName.isBlank().not()) {
            onNextClick(firstName, secondName, type)
        } else {
            if (firstName.isBlank())
                firstNameEditText.error = "You must fill this"
            if (secondName.isBlank())
                secondNameEditText.error = "You must fill this"
        }
    }
}