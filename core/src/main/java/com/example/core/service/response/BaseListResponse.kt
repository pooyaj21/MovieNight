package com.example.core.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseListResponse<T>(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<T>
)