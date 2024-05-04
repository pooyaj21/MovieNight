package com.example.movienight.screen.selectMovie

import com.example.core.model.Movie

sealed class SelectMovieTask {
    data class ListFound(val movies: List<Movie>) : SelectMovieTask()
    object NextList : SelectMovieTask()
    object GoNext : SelectMovieTask()
}