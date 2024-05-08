package com.example.movienight.screen.movieDetail

import com.example.core.model.Genre

sealed class MovieDetailTask {
    data class GenresFound(val genres: List<Genre>) : MovieDetailTask()
}