package com.madonasyombua.dictionarykotlin.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.madonasyombua.dictionarykotlin.domain.CoroutinesContextProvider
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryLocalUseCase
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryUseCase

class DictionaryViewModelFactory (private val dictionaryUseCase: DictionaryUseCase,
                                  private val dictionaryLocalUseCase: DictionaryLocalUseCase,
                                  private val coroutinesContextProvider: CoroutinesContextProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(dictionaryUseCase, dictionaryLocalUseCase, coroutinesContextProvider) as T
    }

}