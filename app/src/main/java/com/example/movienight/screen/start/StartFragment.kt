package com.example.movienight.screen.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movienight.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {

    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return StartView(
            requireContext(),
            onNextClick = { firstName, secondName, type ->
                viewModel.saveNames(firstName, secondName)
                viewModel.saveType(type)
                findNavController().navigate(R.id.nameFragment)
            },
            onSoloClick = { type ->
                viewModel.saveType(type)
                findNavController().navigate(R.id.nameFragment)
            }
        )
    }
}