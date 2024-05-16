package com.example.movienight

import com.example.core.di.coreDiModule
import com.example.movienight.screen.movieDetail.ContentDetailViewViewModel
import com.example.movienight.screen.rollForKnowledge.RollViewModel
import com.example.movienight.screen.selectMovie.SelectContentViewModel
import com.example.movienight.screen.splash.SplashViewModel
import com.example.movienight.screen.start.StartViewModel
import com.example.movienight.screen.startConfirmation.NameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appKoinModule = module {
    includes(coreDiModule)


    viewModel { SplashViewModel() }

    viewModel {
        SelectContentViewModel(
            getListPopularMoviesUseCase = get(),
            getFirstNameUseCase=get(),
            getFoundedContentsUseCase = get(),
            insertFoundedContentsListUseCase = get(),
            getCountOfListCompletedUseCase = get(),
            insertFirstContentsListUseCase = get(),
            insertSecondContentsListUseCase = get()
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

    viewModel {
        RollViewModel(
            getFirstNameUseCase = get(),
            getSecondNameUseCase = get(),
            getMatchingContentContentsUseCase = get()
        )
    }

    viewModel {
        ContentDetailViewViewModel(
            getGenresNameUseCase = get()
        )
    }

}
