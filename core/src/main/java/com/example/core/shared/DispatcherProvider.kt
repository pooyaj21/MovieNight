package com.example.core.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


interface DispatcherProvider {

    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val io: CoroutineDispatcher
}

class DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val io: CoroutineDispatcher = Dispatchers.IO
}