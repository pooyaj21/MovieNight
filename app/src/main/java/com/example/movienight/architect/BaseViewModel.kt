package com.example.movienight.architect

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Task> : ViewModel() {

    private val mutableTaskFlow = MutableStateFlow<Task?>(null)
    val taskFlow =mutableTaskFlow.asStateFlow()

    protected suspend fun setTask(task: Task) = mutableTaskFlow.emit(task)
}