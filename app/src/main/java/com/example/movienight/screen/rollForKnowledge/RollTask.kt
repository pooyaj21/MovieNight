package com.example.movienight.screen.rollForKnowledge

import com.example.core.model.Content

sealed class RollTask {
    data class DataCompleted(
        val firstName: String?,
        val secondName: String?,
        val list: List<Content>?
    ) : RollTask()

    object MovieFounded : RollTask()
}