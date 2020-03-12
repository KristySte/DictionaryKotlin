package com.madonasyombua.dictionarykotlin.domain.repository

import com.madonasyombua.dictionarykotlin.data.datasource.DictionaryDataSource
import com.madonasyombua.dictionarykotlin.data.room.models.WordBody
import com.madonasyombua.dictionarykotlin.data.response.BaseMainResponse
import com.madonasyombua.dictionarykotlin.data.response.BaseResponse
import com.madonasyombua.dictionarykotlin.data.response.Results
import com.madonasyombua.dictionarykotlin.data.response.WordResponse

/**
 * @author Madona
 */

class DictionaryRepository(private val dictionaryDataSource: DictionaryDataSource) {


    suspend fun definition(word: String): Results<BaseResponse> {
        return dictionaryDataSource.definition(word)
    }

}