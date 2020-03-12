package com.madonasyombua.dictionarykotlin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.madonasyombua.dictionarykotlin.data.room.dao.WordDao
import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity

/**
 * @author madona
 * The room database has incredible features
 * 1. it caches data which can be retrieved later when offline mode is activated
 * using the mediator
 * 2. It works well with MVVM and LiveData
 *
 */
@Database(entities = [WordEntity::class], version = 1)
abstract class DictionaryDB : RoomDatabase(){

    abstract fun getWordDao(): WordDao
}