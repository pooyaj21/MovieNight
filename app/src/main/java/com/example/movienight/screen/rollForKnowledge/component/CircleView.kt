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

    private val slicePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.black)
        strokeWidth = 5f // Adjust line thickness as needed
        style = Paint.Style.STROKE
    }
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
            slicePaint.color = resources.getColor(getRandomColor(index))
            bounds.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            canvas.drawArc(bounds, startAngle, sweepAngle, true, slicePaint)

            // Draw the line
            val lineEndX =
                centerX + radius * cos(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()
            val lineEndY =
                centerY + radius * sin(Math.toRadians(startAngle + sweepAngle.toDouble())).toFloat()
            canvas.drawLine(centerX, centerY, lineEndX, lineEndY, linePaint)

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

            // Check if the text width is larger than half of the circle width
            val textHalfWidth = textWidth / 2
            val sliceHalfWidth =
                radius * sin(Math.toRadians(sweepAngle.toDouble() / 2)).toFloat()
            val textPositionX =
                if (textHalfWidth > sliceHalfWidth) centerX + radius * 0.9f else textX

            // Apply rotation transformation to canvas
            canvas.save()
            canvas.rotate(startAngle + sweepAngle / 2, textPositionX + textHalfWidth, textY)

            // Draw the text with the rotation
            canvas.drawText(name, textPositionX, textY, textPaint)

            // Restore canvas to original state
            canvas.restore()

            startAngle += sweepAngle
        }
    }

    private fun getRandomColor(index: Int): Int {
        return if (index % 2 == 0) R.color.mahogany else R.color.gray
    }
}
