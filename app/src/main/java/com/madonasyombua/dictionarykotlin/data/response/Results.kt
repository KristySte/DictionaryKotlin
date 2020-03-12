package com.madonasyombua.dictionarykotlin.data.response

/**
 * @author madona
 * A sealed class allows you to represent constrained hierarchies in which an object can only be of one of the given types.
 * That is, we have a class with a specific number of subclasses.
**/
sealed class Results<out T : Any> {

    data class Success<out T : Any>(val data: T) : Results<T>()
    data class Error(val exception: Throwable) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}