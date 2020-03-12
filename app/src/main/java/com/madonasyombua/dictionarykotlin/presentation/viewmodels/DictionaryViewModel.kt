package com.madonasyombua.dictionarykotlin.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.madonasyombua.dictionarykotlin.data.response.BaseResponse
import com.madonasyombua.dictionarykotlin.data.response.Results
import com.madonasyombua.dictionarykotlin.data.response.Word
import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity
import com.madonasyombua.dictionarykotlin.domain.CoroutinesContextProvider
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryLocalUseCase
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryUseCase
import com.madonasyombua.dictionarykotlin.data.utils.ErrorHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryViewModel(private val dictionaryUseCase: DictionaryUseCase,
                          private val dictionaryLocalUseCase: DictionaryLocalUseCase,

                          private val coroutinesContextProvider: CoroutinesContextProvider
) : MainViewModel() {


    private var currentJob = Job()

    var workManager: WorkManager = WorkManager.getInstance()

    private val _uiModel = MutableLiveData<List<Word>>()
    val uiModel: LiveData<List<Word>>
        get() = _uiModel



    fun getDefinition(word: String) {
        progress.postValue(true)
        launchDefinition(word)
    }


    fun insertOrUpdateWord(word: Word) {

        val wordDB = WordEntity()
        wordDB.authorName = word.authorName
        wordDB.defId = word.defId
        wordDB.definition = word.definition
        wordDB.example = word.example
        wordDB.thumbsDownNumber = word.thumbsDownNumber
        wordDB.thumbsUpNumber = word.thumbsUpNumber
        wordDB.word = word.word
        wordDB.writtenOn = word.writtenOn

    }


    fun getAllWords() {
        launchGetAllWords()
    }

    private fun launchGetAllWords() = GlobalScope.launch(coroutinesContextProvider.io) {
        val result = dictionaryLocalUseCase.getAllWords()
        withContext(coroutinesContextProvider.io) { onResultWords(result) }
    }

    private fun onResultWords(result: List<WordEntity>) {
        val listWords = ArrayList<Word>()
        result.forEach {
            val word = Word(
                it.definition!!,
                "",
                it.thumbsUpNumber!!,
                it.defId!!,
                it.authorName!!,
                it.word!!,
                it.writtenOn!!,
                ArrayList(),
                it.example!!,
                it.thumbsDownNumber!!
            )
            listWords.add(word)
        }
     /*   emitFavWords(listWords)*/
    }

    private fun launchDefinition(word: String) = GlobalScope.launch(coroutinesContextProvider.io) {
        val result = dictionaryUseCase(word)
        withContext(coroutinesContextProvider.io) { onResult(result) }
    }


    private fun onResult(results: Results<BaseResponse>) {
        progress.postValue(false)
        when (results) {
            is Results.Success -> {
                emitUiModel(results.data)
            }
            is Results.Error -> {
                errorBase.postValue(results.exception as ErrorHelper?)
            }
        }
    }
    private fun emitUiModel(data: BaseResponse) =
        GlobalScope.launch(coroutinesContextProvider.main) {
            _uiModel.value = data.wordList
        }


}

