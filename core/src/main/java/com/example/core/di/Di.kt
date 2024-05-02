package com.example.core.di

import com.example.core.service.ServiceProvider
import com.example.core.service.service.MovieService
import org.koin.dsl.module

val coreDiModule = module {
    single { ServiceProvider() }

    factory { get<ServiceProvider>().provide(MovieService::class) }
}