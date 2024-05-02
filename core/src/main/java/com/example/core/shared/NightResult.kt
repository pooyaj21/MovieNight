package com.example.core.shared

sealed class NightResult<out T>  {

    data class Success<T>(val value: T) : NightResult<T>()

    sealed class Error(
        open val message: String?,
        open val cause: Throwable?
    ) : NightResult<Nothing>() {

        data class Local(
            override val message: String?,
            override val cause: Throwable?
        ) : Error(message, cause) {
            override fun toString() = "Error.Local(message=$message, cause=$cause)"
        }

        data class Remote(
            val httpCode: Int,
            override val message: String?,
            override val cause: Throwable?,
        ) : Error(message, cause) {
            override fun toString() =
                "Error.Remote(httpCode=$httpCode, message=$message, cause=$cause)"
        }
    }
}

@Suppress("unused")
inline fun <R, T> NightResult<T>.mapIfSuccess(transform: (value: T) -> R): NightResult<R> {
    return when (this) {
        is NightResult.Success -> NightResult.Success(transform(value))
        is NightResult.Error.Local -> NightResult.Error.Local(message, cause)
        is NightResult.Error.Remote -> NightResult.Error.Remote(httpCode, message, cause)
    }
}