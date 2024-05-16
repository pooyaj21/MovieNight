package com.example.movienight.screen.movieDetail

import com.example.core.model.Genre

sealed class ContentDetailTask {
    data class GenresFound(val genres: List<Genre>) : ContentDetailTask()
}