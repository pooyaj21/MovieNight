package com.example.movienight

import com.example.core.di.coreDiModule
import com.example.movienight.screen.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appKoinModule = module {
    includes(coreDiModule)


    viewModel { SplashViewModel() }
}
