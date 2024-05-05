package com.example.core.di

import com.example.core.data.local.rao.MovieLists
import com.example.core.data.local.rao.Names
import com.example.core.data.local.repository.MovieListRepository
import com.example.core.data.local.repository.MovieListRepositoryImpl
import com.example.core.data.local.repository.NamesRepository
import com.example.core.data.local.repository.NamesRepositoryImpl
import com.example.core.data.remote.repository.MovieRepository
import com.example.core.data.remote.repository.MovieRepositoryImpl
import com.example.core.domain.usecase.*
import com.example.core.service.ServiceProvider
import com.example.core.service.service.MovieService
import com.example.core.shared.DefaultDispatcherProvider
import com.example.core.shared.DispatcherProvider
import org.koin.dsl.module

val coreDiModule = module {
    single { ServiceProvider() }

    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    // Service
    factory { get<ServiceProvider>().provide(MovieService::class) }

    // Data
    single { MovieLists() }

    single { Names() }

    factory<MovieRepository> { MovieRepositoryImpl(movieService = get()) }

    factory<MovieListRepository> { MovieListRepositoryImpl(movieLists = get()) }

    factory<NamesRepository> { NamesRepositoryImpl(names = get()) }

    // Domain
    factory<GetListPopularMoviesUseCase> {
        GetListPopularMoviesUseCaseImpl(
            movieRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetMatchingMoviesUseCase> {
        GetMatchingMoviesUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<InsertFoundedMoviesListUseCase> {
        InsertFoundedMoviesListUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<InsertFirstMoviesListUseCase> {
        InsertFirstMoviesListUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<InsertSecondMoviesListUseCase> {
        InsertSecondMoviesListUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<GetCountOfListCompletedUseCase> {
        GetCountOfListCompletedUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<GetFoundedMoviesUseCase> {
        GetFoundedMoviesUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<InsertFoundedMoviesListUseCase> {
        InsertFoundedMoviesListUseCaseImpl(
            movieListRepository = get()
        )
    }

    factory<InsertNamesUseCase> {
        InsertNamesUseCaseImpl(
            namesRepository = get()
        )
    }

    factory<GetFirstNameUseCase> {
        GetFirstNameUseCaseImpl(
            namesRepository = get()
        )
    }

    factory<GetSecondNameUseCase> {
        GetSecondNameUseCaseImpl(
            namesRepository = get()
        )
    }
}