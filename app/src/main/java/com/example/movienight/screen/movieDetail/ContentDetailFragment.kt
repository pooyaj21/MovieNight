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

class ContentDetailFragment : DialogFragment() {

    private val args: ContentDetailFragmentArgs by navArgs()

    private val viewModel: ContentDetailViewViewModel by viewModel()

    private var contentDetailView: ContentDetailView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel.getGenres(args.content.genreIds)
        contentDetailView = ContentDetailView(requireContext())
        return contentDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is ContentDetailTask.GenresFound -> contentDetailView?.success(args.content.toUiContent(task.genres))
                null -> contentDetailView?.loading()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}