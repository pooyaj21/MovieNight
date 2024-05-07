package com.example.movienight.screen.movieDetail

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class MovieDetailFragment : DialogFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return MovieDetailView(requireContext()).apply {
            success(args.movie)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Removing default rectangle background
        (view.parent as View).run {
            backgroundTintMode = PorterDuff.Mode.CLEAR
            backgroundTintList = ColorStateList.valueOf(android.graphics.Color.TRANSPARENT)
            setBackgroundColor(android.graphics.Color.TRANSPARENT)
        }
    }
}