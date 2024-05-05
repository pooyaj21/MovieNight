package com.example.movienight.screen.rollForKnowledge.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.core.model.Movie
import com.example.movienight.exctation.ImageUrl
import kotlin.math.cos
import kotlin.math.sin

@SuppressLint("ViewConstructor")
class CircleView(context: Context, private val items: List<Movie>) : View(context) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds: RectF = RectF()
    private val textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val imagePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (Math.min(width, height) / 2f) * 0.8f // Adjust the 0.8f factor as needed

        var startAngle = 0f

        for (movie in items) {
            val sweepAngle = 360f / items.size

            // Draw the slice
            paint.color = getRandomColor()
            bounds.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            canvas.drawArc(bounds, startAngle, sweepAngle, true, paint)

            val textBounds = Rect()
            val name = movie.title
            textPaint.textSize = 30f // Adjust text size as needed
            textPaint.color = Color.WHITE
            textPaint.getTextBounds(name, 0, name.length, textBounds)
            val textWidth = textPaint.measureText(name)
            val textX =
                centerX + radius / 2 * cos(Math.toRadians((startAngle + sweepAngle).toDouble()))
                    .toFloat() - textWidth / 2
            val textY =
                centerY + radius / 2 * sin(Math.toRadians((startAngle + sweepAngle ).toDouble()))
                    .toFloat() + textBounds.height() / 2
            canvas.drawText(name, textX, textY, textPaint)

            Glide.with(context)
                .asBitmap()
                .load(ImageUrl.basUrl + movie.backdropImage)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        // Do something with the loaded bitmap
                        val scaledBitmap = Bitmap.createScaledBitmap(resource, 100, 100, false)
                        val imageX = centerX + radius / 2 * cos(Math.toRadians((startAngle + sweepAngle / 2).toDouble())).toFloat() - scaledBitmap.width / 2
                        val imageY = centerY + radius / 2 * sin(Math.toRadians((startAngle + sweepAngle / 2).toDouble())).toFloat() - scaledBitmap.height / 2
                        // Draw the scaled bitmap inside the slice
                        canvas.drawBitmap(scaledBitmap, imageX, imageY, imagePaint)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        // Placeholder or previous loaded image has been cleared
                    }
                })

            startAngle += sweepAngle
        }
    }

    private fun getRandomColor(): Int {
        return (Math.random() * 16777215f).toInt() or (0xFF shl 24)
    }
}
