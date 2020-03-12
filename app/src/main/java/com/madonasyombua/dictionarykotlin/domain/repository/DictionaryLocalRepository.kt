package com.madonasyombua.dictionarykotlin.domain.repository

import com.madonasyombua.dictionarykotlin.data.room.dao.WordDao
import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlinx.coroutines.*

/**
 * @author madona
 *
 * The repository class is responsible for interacting with the Room database on behalf of the ViewModel
 * We basically create methods that use the DAO to insert, delete and query our data.
 * As you can see we call WordDao inside the class
 * */
class DictionaryLocalRepository (private val wordDao: WordDao){


    suspend fun getAllWordEntries(): List<WordEntity> {
        return withContext(Dispatchers.Default) {
            wordDao.getWordList()
        }
    }

    suspend fun getWordById(defId: Int): WordEntity? {
        return withContext(Dispatchers.Default) {
            wordDao.getWordByDefId(defId)
        }
    }


    fun insertWord(wordEntity: WordEntity): Job {
        return GlobalScope.launch {
            wordDao.saveWord(wordEntity)
        }
    }



}