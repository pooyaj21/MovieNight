package com.example.core.di

import com.example.core.data.local.rao.ContentLists
import com.example.core.data.local.rao.Names
import com.example.core.data.local.repository.*
import com.example.core.data.local.repository.NamesRepositoryImpl
import com.example.core.data.remote.repository.*
import com.example.core.data.remote.repository.MovieRepositoryImpl
import com.example.core.data.remote.repository.TvSeriesRepositoryImpl
import com.example.core.domain.usecase.*
import com.example.core.service.ServiceProvider
import com.example.core.service.service.MovieService
import com.example.core.service.service.TvSeriesService
import com.example.core.shared.DefaultDispatcherProvider
import com.example.core.shared.DispatcherProvider
import org.koin.dsl.module

val coreDiModule = module {
    single { ServiceProvider() }

    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    // Service
    factory { get<ServiceProvider>().provide(MovieService::class) }

    factory { get<ServiceProvider>().provide(TvSeriesService::class) }

    // Data
    single { ContentLists() }

    single { Names() }

    factory<MovieRepository> { MovieRepositoryImpl(movieService = get()) }

    factory<TvSeriesRepository> { TvSeriesRepositoryImpl(tvSeriesService = get()) }

    factory<ContentListRepository> { ContentListRepositoryImpl(contentLists = get()) }

    factory<NamesRepository> { NamesRepositoryImpl(names = get()) }

    // Domain
    factory<GetListPopularMoviesUseCase> {
        GetListPopularMoviesUseCaseImpl(
            movieRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetListPopularTvSeriesUseCase> {
        GetListPopularTvSeriesUseCaseImpl(
            tvSeriesRepository = get(),
            dispatcherProvider = get()
        )
    }

    factory<GetMatchingContentContentsUseCase> {
        GetMatchingContentContentsUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<InsertFoundedContentsListUseCase> {
        InsertFoundedContentsListUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<InsertFirstContentsListUseCase> {
        InsertFirstContentsListUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<InsertSecondContentsListUseCase> {
        InsertSecondContentsListUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<GetCountOfListCompletedUseCase> {
        GetCountOfListCompletedUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<GetFoundedContentsUseCase> {
        GetFoundedContentsUseCaseImpl(
            contentListRepository = get()
        )
    }

    factory<InsertFoundedContentsListUseCase> {
        InsertFoundedContentsListUseCaseImpl(
            contentListRepository = get()
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

    factory<GetGenresNameUseCase> {
        GetGenresNameUseCaseImpl(
            movieRepository = get(),
            dispatcherProvider = get()
        )
    }
}