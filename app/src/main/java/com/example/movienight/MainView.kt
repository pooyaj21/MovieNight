package com.example.movienight

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

@SuppressLint("ViewConstructor")
class MainView(
    activity: AppCompatActivity
) : FrameLayout(activity) {
    private val fragmentContainer =
        FrameLayout(context).apply { id = R.id.navigation_component_container }

    init {
        id = R.id.navigation_component_container12
        setBackgroundColor(Color.BLACK)
        addView(
            fragmentContainer,
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )
        val navHostFragment = NavHostFragment.create(
            graphResId = R.navigation.nav_graph
        )
        if (activity.supportFragmentManager.fragments.size == 0) {
            activity.supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commitAllowingStateLoss()
        }
    }
}
