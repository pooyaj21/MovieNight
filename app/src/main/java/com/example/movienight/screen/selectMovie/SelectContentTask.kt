package com.example.movienight.screen.selectMovie

import com.example.core.model.Content

sealed class SelectContentTask {
    data class ListFound(val contents: List<Content>) : SelectContentTask()
    object NextList : SelectContentTask()
    object GoNext : SelectContentTask()
}