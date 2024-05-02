package com.example.movienight.screen.splash

import androidx.lifecycle.viewModelScope
import com.example.movienight.architect.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashTask>() {

    fun animationEnded() {
        viewModelScope.launch { setTask(SplashTask.GoNext) }
    }
}