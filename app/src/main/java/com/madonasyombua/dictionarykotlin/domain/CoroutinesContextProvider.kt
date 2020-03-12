package com.madonasyombua.dictionarykotlin.domain


import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.Dispatchers

/**
 * @author madona
 * more documentation on Dispatcher (https://kotlinlang.org/docs/reference/coroutines/coroutine-context-and-dispatchers.html)
 */

data class CoroutinesContextProvider(

    val main: CoroutineContext = Dispatchers.Main,
    val io: CoroutineContext = Dispatchers.Default
)