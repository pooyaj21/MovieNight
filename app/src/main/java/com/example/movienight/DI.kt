package com.example.movienight

import com.example.core.di.coreDiModule
import org.koin.dsl.module

val appKoinModule = module {
    includes(coreDiModule)

}
