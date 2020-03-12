package com.madonasyombua.dictionarykotlin.domain.usecases

import com.madonasyombua.dictionarykotlin.data.response.BaseResponse
import com.madonasyombua.dictionarykotlin.data.response.Results
import com.madonasyombua.dictionarykotlin.domain.repository.DictionaryRepository

/***
 * @author madona
 * The purpose of the Use Cases is to request data to repositories and turn into something usable for the View.
 *
 */
class DictionaryUseCase(private val dictionaryRepository: DictionaryRepository) {
    suspend operator fun invoke(word: String): Results<BaseResponse> {
        return dictionaryRepository.definition(word)
    }
}