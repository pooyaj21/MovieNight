package com.example.movienight.screen.rollForKnowledge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class RollFragment : Fragment() {

    private val viewModel: RollViewModel by viewModel()

    private var rollView: RollView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rollView = RollView(
            requireContext()
        )
        return rollView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskFlow.onEach { task ->
            when (task) {
                is RollTask.DataCompleted -> {
                    rollView?.success(task.firstName, task.secondName, task.list)
                }
                null -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        rollView = null
    }
}