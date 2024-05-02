package com.example.core.di

import com.example.core.data.local.repository.MovieLists
import com.example.core.data.remote.repository.MovieRepository
import com.example.core.data.remote.repository.MovieRepositoryImpl
import com.example.core.domain.usecase.*
import com.example.core.service.ServiceProvider
import com.example.core.service.service.MovieService
import org.koin.dsl.module

val coreDiModule = module {
    single { ServiceProvider() }

    // Service
    factory { get<ServiceProvider>().provide(MovieService::class) }

    // Data
    factory<MovieRepository> { MovieRepositoryImpl(movieService = get()) }

    // Domain
    single { MovieLists() }

    factory<GetListPopularMoviesUseCase> {
        GetListPopularMoviesUseCaseImpl(
            movieRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetMatchingMoviesUseCase> {
        GetMatchingMoviesUseCaseImpl(
            movieLists = get()
        )
    }

    factory<InsertMoviesListUseCase> {
        InsertMoviesListUseCaseImpl(
            movieLists = get()
        )
    }

    factory<GetCountOfListCompletedUseCase> {
        GetCountOfListCompletedUseCaseImpl(
            movieLists = get()
        )
    }

}