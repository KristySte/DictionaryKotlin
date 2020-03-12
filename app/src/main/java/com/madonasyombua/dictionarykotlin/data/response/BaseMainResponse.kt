package com.madonasyombua.dictionarykotlin.data.response


/**
 * @author madona
 * A Data Class is like a regular class but with some additional functions.
 * The compiler automatically generates a default accessor and mutator for all the mutable properties,
 * and a accessors(only) for all the read-only properties of the data class.
 */
data class BaseMainResponse<T>(val code: Int, val data: T, val message: String) {

    fun isSuccess(): Boolean {
        return 200 == code
    }

}