package com.example.core.service

import com.example.core.Configuration
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

internal class ServiceCreator private constructor(private val builder: Builder) {

    private var retrofit: Retrofit

    val baseUrl: String
        get() = retrofit.baseUrl().toString()

    init {
        val okHttpClient = OkHttpClient.Builder().apply {
            followRedirects(builder.followRedirects)
            followSslRedirects(builder.followRedirects)
            retryOnConnectionFailure(builder.retryOnConnectionFailure)
            connectTimeout(builder.timeout, TimeUnit.SECONDS)
            writeTimeout(builder.timeout, TimeUnit.SECONDS)
            readTimeout(builder.timeout, TimeUnit.SECONDS)
            builder.loggingInterceptor?.let { addNetworkInterceptor(it) }
            builder.interceptors.forEach { addInterceptor(it) }
        }.build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(builder.converterFactory)
            .baseUrl(builder.baseUrl)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    /**
     * The builder class for configuring and constructing the `ServiceCreator`.
     */
    class Builder(
        val baseUrl: String,
        val converterFactory: Converter.Factory,
    ) {
        var followRedirects = true
            private set
        var retryOnConnectionFailure = true
            private set
        var timeout = 30L
            private set
        var loggingInterceptor: HttpLoggingInterceptor? = null
            private set
        var interceptors = mutableListOf<Interceptor>()
            private set

        fun followRedirects(follow: Boolean) = apply {
            followRedirects = follow
        }

        fun retryOnConnectionFailure(retry: Boolean) = apply {
            retryOnConnectionFailure = retry
        }

        fun timeout(seconds: Long) = apply {
            timeout = seconds
        }

        fun loggingInterceptor(interceptor: HttpLoggingInterceptor) = apply {
            loggingInterceptor = interceptor
        }

        fun addInterceptor(interceptor: Interceptor) = apply {
            interceptors.add(interceptor)
        }

        fun build() = ServiceCreator(this)

        companion object {
            @OptIn(ExperimentalSerializationApi::class)
            private val jsonFormatter = Json {
                encodeDefaults =
                    false // In encodings (for request models) we want to serialize default values of models
                explicitNulls =
                    false // In encodings (for request models) we want to ignore serialization of null values
                ignoreUnknownKeys =
                    true // In decoding (for response models) we want to ignore unknown values
            }

            internal fun buildDefaultServiceCreator(
                vararg interceptors: Interceptor
            ): ServiceCreator {
                return Builder(
                    baseUrl = Configuration.baseUrl,
                    converterFactory = jsonFormatter.asConverterFactory("application/json".toMediaType())
                ).apply {
                    followRedirects(true)
                    retryOnConnectionFailure(true)
                    timeout(seconds = 30)
                    loggingInterceptor(
                        HttpLoggingInterceptor(logger = BeautifyLogger()).apply {
                            level = Level.BODY
                        }
                    )
                    interceptors.forEach { addInterceptor(it) }
                }.build()
            }
        }
    }
}