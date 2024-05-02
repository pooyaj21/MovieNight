package com.example.core.shared.kotlin

import com.example.core.shared.NightResult
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> suspendSafeDataResult(block: suspend () -> NightResult<T>): NightResult<T> {
    return try {
        block()
    } catch (e: CancellationException) {
        // Propagate cancellation exceptions to maintain structured concurrency.
        throw e
    } catch (e: Exception) {
        NightResult.Error.Local(e.localizedMessage, cause = e)
    }
}