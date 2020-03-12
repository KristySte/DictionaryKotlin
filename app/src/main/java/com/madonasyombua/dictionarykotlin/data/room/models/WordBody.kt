package com.madonasyombua.dictionarykotlin.data.room.models

/**
 * @author madona
 * The class word body hold constant value
 */
class WordBody(
    val definition: String,
    val thumbs_up: Int,
    val author: String,
    val word: String,
    val defid: Int,
    val written_on: String,
    val example: String,
    val thumbs_down: Int
)
