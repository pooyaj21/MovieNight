package com.example.movienight.screen.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : DialogFragment() {

    private val args: MovieDetailFragmentArgs by navArgs()

    private val viewModel: MovieDetailViewModel by viewModel()

    private var movieDetailView: MovieDetailView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel.getGenres(args.movie.genreIds)
        movieDetailView = MovieDetailView(requireContext())
        return movieDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is MovieDetailTask.GenresFound -> movieDetailView?.success(args.movie.toUiMovie(task.genres))
                null -> movieDetailView?.loading()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}