package com.example.movienight.screen.selectMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectMovieFragment : Fragment() {

    private val viewModel: SelectMovieViewModel by viewModel()

    private var selectMovieView: SelectMovieView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectMovieView = SelectMovieView(requireContext())
        return selectMovieView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach {
            when (it) {
                is SelectMovieTask.ListFound -> selectMovieView?.submitList(it.movies)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        selectMovieView = null
    }
}