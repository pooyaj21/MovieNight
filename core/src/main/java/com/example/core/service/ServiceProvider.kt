package com.example.core.service

import com.example.core.Configuration
import com.example.core.service.ServiceCreator.Builder.Companion.buildDefaultServiceCreator
import kotlin.reflect.KClass

internal class ServiceProvider(
) {

    private val tokenizedServiceCreator: ServiceCreator

    init {
        val headerInterceptor =
            HeaderInterceptor(listOf(Configuration.acceptHeader, Configuration.autHeader))
        tokenizedServiceCreator = buildDefaultServiceCreator(headerInterceptor)
    }

    fun <T : Any> provide(service: KClass<T>): T = tokenizedServiceCreator.create(service.java)
}