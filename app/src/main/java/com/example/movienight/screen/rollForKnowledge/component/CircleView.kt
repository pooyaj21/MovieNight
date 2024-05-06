package com.example.movienight.screen.rollForKnowledge.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View
import com.example.core.model.Movie
import com.example.movienight.R
import kotlin.math.cos
import kotlin.math.sin

@SuppressLint("ViewConstructor")
class CircleView(context: Context, private val items: List<Movie>) : View(context) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds: RectF = RectF()
    private val textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = x + width / 2f
        val centerY = y + height / 2f
        val radius = (width.coerceAtMost(height) / 2f) * 0.8f // Adjust the 0.8f factor as needed

        var startAngle = -90f // Start from the top

        items.forEachIndexed { index, movie ->
            val sweepAngle = 360f / items.size

            // Draw the slice
            paint.color = resources.getColor(getRandomColor(index))
            bounds.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            canvas.drawArc(bounds, startAngle, sweepAngle, true, paint)

            // Draw the text
            val name = movie.title
            textPaint.textSize = 30f // Adjust text size as needed
            textPaint.color = Color.WHITE
            val textWidth = textPaint.measureText(name)
            val textX =
                centerX + radius / 2 * cos(Math.toRadians(startAngle + sweepAngle / 2.toDouble()))
                    .toFloat() - textWidth / 2
            val textY =
                centerY + radius / 2 * sin(Math.toRadians(startAngle + sweepAngle / 2.toDouble()))
                    .toFloat() + textPaint.textSize / 2 // Center vertically
            canvas.drawText(name, textX, textY, textPaint)

            startAngle += sweepAngle
        }
    }

    private fun getRandomColor(index: Int): Int {
        return if (index % 2 == 0) R.color.mahogany else R.color.gray
    }
}
