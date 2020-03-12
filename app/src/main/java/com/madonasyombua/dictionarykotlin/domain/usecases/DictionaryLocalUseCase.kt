package com.madonasyombua.dictionarykotlin.domain.usecases

import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity
import com.madonasyombua.dictionarykotlin.domain.repository.DictionaryLocalRepository

/**
 * @author madona
 * The purpose of the Use Cases is to request data to repositories and turn into something usable for the View.
 * Hence we will be mapping and getting more from the repository Dictionary Local Repository Class
 *
 */
class DictionaryLocalUseCase(private val dictionaryLocalRepository: DictionaryLocalRepository){


    // method to get all words from the Word Entity
    suspend fun getAllWords(): List<WordEntity>{
        return  dictionaryLocalRepository.getAllWordEntries()
    }

}