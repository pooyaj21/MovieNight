package com.example.movienight.screen.start

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.*
import androidx.constraintlayout.widget.ConstraintSet
import com.example.movienight.R
import com.example.movienight.Screen
import com.example.movienight.components.AppEditTextView
import com.example.movienight.exctation.dpToPx

@SuppressLint("ViewConstructor")
class StartView(
    context: Context,
    private val onNextClick: (firstName: String, secondName: String) -> Unit
) : ConstraintLayout(context) {

    private val editTextSize = Screen.size.width * 0.8

    private val firstNameEditText = AppEditTextView(context).apply {
        id = generateViewId()
        hint = "First person name"
    }
    private val secondNameEditText = AppEditTextView(context).apply {
        id = generateViewId()
        hint = "Second person name"
    }
    private val andTextView = TextView(context).apply {
        id = generateViewId()
        setTextColor(Color.WHITE)
        text = "&"
        textSize = 25F
    }
    private val letsGoButton = Button(context).apply {
        id = generateViewId()
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(resources.getColor(R.color.mahogany,resources.newTheme()))
            setStroke(1.dpToPx, Color.WHITE)
            cornerRadius = 12.dpToPx.toFloat()
        }
        text = "Lets Go"
        setOnClickListener {
            letsGo()
        }
    }


    init {
        setBackgroundColor(Color.BLACK)
        addView(firstNameEditText, LayoutParams(editTextSize.toInt(), WRAP_CONTENT))
        addView(andTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(secondNameEditText, LayoutParams(editTextSize.toInt(), WRAP_CONTENT))
        addView(letsGoButton, LayoutParams((editTextSize.toInt()) / 2, WRAP_CONTENT))

        val constraints = ConstraintSet()
        constraints.clone(this)

        // first name EditText
        constraints.connect(
            firstNameEditText.id, START, PARENT_ID, START, 0
        )
        constraints.connect(
            firstNameEditText.id, END, PARENT_ID, END, 0
        )
        constraints.connect(
            firstNameEditText.id, TOP, PARENT_ID, TOP, 200.dpToPx
        )

        // & TextView
        constraints.connect(
            andTextView.id, START, firstNameEditText.id, START, 0
        )
        constraints.connect(
            andTextView.id, END, firstNameEditText.id, END, 0
        )
        constraints.connect(
            andTextView.id, TOP, firstNameEditText.id, BOTTOM, 20.dpToPx
        )
        constraints.connect(
            andTextView.id, BOTTOM, secondNameEditText.id, TOP, 20.dpToPx
        )

        // second name EditText
        constraints.connect(
            secondNameEditText.id, START, PARENT_ID, START, 0
        )
        constraints.connect(
            secondNameEditText.id, END, PARENT_ID, END, 0
        )
        constraints.connect(
            secondNameEditText.id, BOTTOM, letsGoButton.id, TOP, 10.dpToPx
        )

        // Let's Go Button
        constraints.connect(
            letsGoButton.id, START, PARENT_ID, START, 0
        )
        constraints.connect(
            letsGoButton.id, END, PARENT_ID, END, 0
        )
        constraints.connect(
            letsGoButton.id, TOP, secondNameEditText.id, BOTTOM, 150.dpToPx
        )

        constraints.applyTo(this)
    }

    private fun letsGo() {
        firstNameEditText.error = null
        secondNameEditText.error = null
        val firstName = firstNameEditText.text
        val secondName = secondNameEditText.text
        if (firstName.isNullOrBlank().not() && secondName.isNullOrBlank().not()) {
            onNextClick(firstName.toString(), secondName.toString())
        } else {
            if (firstName.isNullOrBlank())
                firstNameEditText.error = "You must fill this"
            if (secondName.isNullOrBlank())
                secondNameEditText.error = "You must fill this"
        }
    }
}