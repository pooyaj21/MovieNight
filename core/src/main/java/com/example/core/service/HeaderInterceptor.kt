package com.example.core.service

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class HeaderInterceptor(private val headers: List<Pair<String, String>>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder: Request.Builder = chain.request().newBuilder()

        for (pair in headers) {
            requestBuilder.addHeader(pair.first, pair.second)
        }

        return chain.proceed(requestBuilder.build())
    }
}