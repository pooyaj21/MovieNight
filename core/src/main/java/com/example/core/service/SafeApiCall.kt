package com.example.core.service

import com.example.core.shared.NightResult
import retrofit2.HttpException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): NightResult<T> {
    return try {
        val response = apiCall.invoke()
        NightResult.Success(response)
    } catch (e: HttpException) {
        NightResult.Error.Remote(
            httpCode = e.code(),
            message = e.message,
            cause = e
        )
    } catch (e: Exception) {
        NightResult.Error.Local(message = e.localizedMessage, cause = e)
    }
}
