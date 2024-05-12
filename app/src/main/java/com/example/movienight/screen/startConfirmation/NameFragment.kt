package com.example.movienight.screen.startConfirmation

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


class NameFragment : Fragment() {

    private val viewModel: NameViewModel by viewModel()

    private var nameView: NameView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nameView = NameView(
            requireContext(),
            goNext = {
                findNavController().navigate(R.id.selectMovieFragment)
            }
        )
        return nameView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is NameTask.ChosenName -> nameView?.setName(task.name)
                null -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}