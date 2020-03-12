package com.madonasyombua.dictionarykotlin.data.utils

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.madonasyombua.dictionarykotlin.data.response.Results
import com.madonasyombua.dictionarykotlin.data.response.Word
import com.madonasyombua.dictionarykotlin.presentation.ui.activities.DefinitionActivity
import retrofit2.HttpException
import java.io.IOException

/**
 * @author madona
 * Suspending functions can be created as standard Kotlin functions, but we need to be aware that we can only call them from within a coroutine.
**/
suspend fun <T : Any> safeApiCall(call: suspend () -> Results<T>): Results<T> {
    return try {
        call()
    } catch (e: Exception) {
        when (e) {
            is IOException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.NETWORK))
            }
            is HttpException -> {
                Results.Error(ErrorHelper(ERROR_STATUS.UNKNOWN))
            }
            else -> {
                Results.Error(ErrorHelper(ERROR_STATUS.ERR, e.message))
            }
        }
    }
}

val <T> T.exhaustive: T
    get() = this


fun <D : Any, T : LiveData<D>> T.observeWith(owner: LifecycleOwner, receiver: (D) -> Unit) {
    observe(owner, Observer<D> {
        if (it != null) {
            receiver(it)
        }
    })
}


enum class ERROR_STATUS {
    NETWORK,
    UNKNOWN,
    ERR
}

fun Context.DefinitionDetailIntent(word: Word): Intent {
    return Intent(this, DefinitionActivity::class.java).apply {
        val wordJsonBody = Gson().toJson(word)
        putExtra(Constants.INTENT_WORD_JSON, wordJsonBody)
    }
}

