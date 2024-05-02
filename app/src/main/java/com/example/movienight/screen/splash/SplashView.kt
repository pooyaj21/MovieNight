package com.example.movienight.screen.splash

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.movienight.R
import com.example.movienight.exctation.dpToPx

@SuppressLint("ViewConstructor")
class SplashView(
    context: Context,
    private val onAnimationEnd: () -> Unit
) : FrameLayout(context) {
    private var logoLottie: LottieAnimationView

    init {
        logoLottie = createLogoLottie()
        val layoutParams = LayoutParams(200.dpToPx, 200.dpToPx)
        layoutParams.gravity = Gravity.CENTER
        addView(logoLottie, layoutParams)
    }

    private fun createLogoLottie() = LottieAnimationView(context).apply {
        setAnimation(R.raw.splash_animation)
            playAnimation()
        addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // Nothing
            }

            override fun onAnimationEnd(animation: Animator) {
                onAnimationEnd.invoke()
            }

            override fun onAnimationCancel(animation: Animator) {
                // Nothing
            }

            override fun onAnimationRepeat(animation: Animator) {
                // Nothing
            }
        })
    }
}