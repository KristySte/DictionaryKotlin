package com.madonasyombua.dictionarykotlin.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @author Madona
 * Entity Class - One of Room annotations and a main component
 * The Entity class defines our database table, our table name is word
 * We can debug the database using DB Browser for SQLite and see what the table has
 *
 * */


@Entity(tableName = "word")
class WordEntity{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var definition: String? = null
    var thumbsUpNumber: Int? = null
    var defId: Int? = null
    var authorName: String? = null
    var word: String? = null
    var writtenOn: String? = null
    var example: String? = null
    var thumbsDownNumber: Int? = null



}