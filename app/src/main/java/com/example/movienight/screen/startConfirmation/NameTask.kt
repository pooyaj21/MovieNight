package com.example.movienight.screen.startConfirmation

sealed class NameTask {
    data class ChosenName(val name: String?) : NameTask()
}
