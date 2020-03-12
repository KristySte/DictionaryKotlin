package com.madonasyombua.dictionarykotlin.data.response

/**
 * @author madona
 * A Data Class is like a regular class but with some additional functions.
 * The compiler automatically generates a default accessor and mutator for all the mutable properties,
 * and a accessors(only) for all the read-only properties of the data class.
 */
data class WordResponse(
    val definition: String,
    val thumbs_up: Int,
    val author: String,
    val word: String,
    val defid: Int,
    val written_on: String,
    val example: String,
    val thumbs_down: Int,
    val id: Int
)