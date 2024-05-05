package com.example.movienight

import com.example.core.di.coreDiModule
import com.example.movienight.screen.selectMovie.SelectMovieViewModel
import com.example.movienight.screen.splash.SplashViewModel
import com.example.movienight.screen.start.StartViewModel
import com.example.movienight.screen.startConfirmation.NameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appKoinModule = module {
    includes(coreDiModule)


    viewModel { SplashViewModel() }

    viewModel {
        SelectMovieViewModel(
            getListPopularMoviesUseCase = get(),
            getFoundedMoviesUseCase = get(),
            insertFoundedMoviesListUseCase = get(),
            getCountOfListCompletedUseCase = get(),
            insertFirstMoviesListUseCase = get(),
            insertSecondMoviesListUseCase = get()
        )
    }

    viewModel { StartViewModel(insertNamesUseCase = get()) }


    viewModel {
        NameViewModel(
            getCountOfListCompletedUseCase = get(),
            getFirstNameUseCase = get(),
            getSecondNameUseCase = get()
        )
    }

}
