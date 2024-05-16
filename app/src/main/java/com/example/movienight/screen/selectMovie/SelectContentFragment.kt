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

class SelectContentFragment : Fragment() {

    private val viewModel: SelectContentViewModel by viewModel()

    private var selectContentView: SelectContentView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (selectContentView == null) {
            selectContentView = SelectContentView(
                requireContext(),
                onSwipeCompleted = { favorite ->
                    viewModel.listEnded(favorite)
                },
                onContentClickListener = { content ->
                    findNavController().navigate(
                        SelectContentFragmentDirections.actionContentDetailSelectContentFragment(content)
                    )
                }
            )
        }
        return selectContentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is SelectContentTask.ListFound -> selectContentView?.submitList(task.contents)
                SelectContentTask.NextList -> {
                    findNavController().navigate(R.id.nameFragment)
                }
                SelectContentTask.GoNext -> {
                    findNavController().navigate(R.id.rollFragment)
                }
                null -> {
                    selectContentView?.loading()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        selectContentView = null
    }
}