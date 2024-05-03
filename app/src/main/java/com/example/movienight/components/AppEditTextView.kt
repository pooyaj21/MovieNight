package com.example.movienight.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.TextUtils
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import com.example.movienight.exctation.dpToPx

class AppEditTextView(context: Context) : FrameLayout(context) {

    private val editTextView = EditText(context).apply {
        background = null
        setTextColor(Color.BLACK)
        setHintTextColor(Color.WHITE)
    }

    var error: String? = null
        set(value) {
            field = value
            editTextView.error = value
        }

    var hint: String? = null
        set(value) {
            field = value
            editTextView.hint = value
        }

    val text: String get() = editTextView.text.toString()


    init {
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.GRAY)
            setStroke(1.dpToPx, Color.WHITE)
            cornerRadius = 12.dpToPx.toFloat()
        }
        val layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).apply {
                setPadding(2.dpToPx, 2.dpToPx, 2.dpToPx, 2.dpToPx)
                gravity = Gravity.CENTER
            }
        addView(editTextView, layoutParams)

    }

    fun setOnDoneClickListener(listener: () -> Unit) {
        editTextView.imeOptions = EditorInfo.IME_ACTION_DONE
        editTextView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                listener.invoke()
            }
            false
        }
    }

    fun singleLine() {
        editTextView.setSingleLine()
        editTextView.setLines(1)
        editTextView.maxLines = 1
        editTextView.ellipsize = TextUtils.TruncateAt.END
    }

}