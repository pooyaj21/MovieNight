package com.example.movienight.screen.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movienight.R

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return StartView(
            requireContext(),
            onNextClick = { firstName, secondName ->
                findNavController().navigate(R.id.selectMovieFragment)
            }
        )
    }
}