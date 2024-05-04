package com.example.movienight.exctation

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.core.model.Image
import com.example.movienight.R

fun ImageView.load(image: Image?, @DrawableRes placeholder: Int = R.color.white) {
    if (image == null) {
        setImageResource(android.R.color.transparent)
        return
    }
    Glide.with(context)
        .asBitmap()
        .transition(BitmapTransitionOptions.withCrossFade(crossFadeFactory))
        .placeholder(placeholder)
        .load(ImageUrl.basUrl + image.url)
        .into(this)
}

private val crossFadeFactory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

object ImageUrl {
    const val basUrl = "https://image.tmdb.org/t/p/w500/"
}