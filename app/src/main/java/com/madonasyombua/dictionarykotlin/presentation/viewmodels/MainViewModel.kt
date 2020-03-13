package com.madonasyombua.dictionarykotlin.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.madonasyombua.dictionarykotlin.data.utils.ErrorHelper

/**
 * @author Madona
 * The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
 * The ViewModel class allows data to survive configuration changes such as screen rotations.
 */

open class MainViewModel : ViewModel(){

    val errorBase = MutableLiveData<ErrorHelper>()
    val progress = MutableLiveData<Boolean>()


    fun progress(): MutableLiveData<Boolean> {
        return progress
    }

    override fun onCleared() {
        super.onCleared()

    }

}