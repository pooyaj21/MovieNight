package com.example.movienight.screen.startConfirmation

sealed class NameTask {
    data class ChosenName(val tag: String) : NameTask()
}
