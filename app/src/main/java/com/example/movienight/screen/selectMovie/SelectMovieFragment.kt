package com.example.movienight.screen.selectMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movienight.R
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
        if (selectMovieView == null) {
            selectMovieView = SelectMovieView(
                requireContext(),
                onSwipeCompleted = { favorite ->
                    viewModel.listEnded(favorite)
                },
                onMovieClickListener = { movie ->
                    findNavController().navigate(
                        SelectMovieFragmentDirections.actionMovieDetailSelectMovieFragment(movie)
                    )
                }
            )
        }
        return selectMovieView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is SelectMovieTask.ListFound -> selectMovieView?.submitList(task.movies)
                SelectMovieTask.NextList -> {
                    findNavController().navigate(R.id.nameFragment)
                }
                SelectMovieTask.GoNext -> {
                    findNavController().navigate(R.id.rollFragment)
                }
                null -> {
                    selectMovieView?.loading()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        selectMovieView = null
    }
}