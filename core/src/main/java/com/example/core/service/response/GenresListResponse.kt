package com.example.core.service.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresListResponse(
    @SerialName("genres") val genres: List<GenreResponse>
) {

    @Serializable
    data class GenreResponse(
        @SerialName("id") val id: Int,
        @SerialName("name") val name: String
    )
}