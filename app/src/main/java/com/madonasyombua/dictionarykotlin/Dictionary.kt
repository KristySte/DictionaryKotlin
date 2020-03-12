package com.madonasyombua.dictionarykotlin

import android.app.Application
import androidx.room.Room
import com.madonasyombua.dictionarykotlin.data.datasource.DictionaryDataSource
import com.madonasyombua.dictionarykotlin.data.room.DictionaryDB
import com.madonasyombua.dictionarykotlin.data.services.Api
import com.madonasyombua.dictionarykotlin.data.services.ApiCreator
import com.madonasyombua.dictionarykotlin.domain.CoroutinesContextProvider
import com.madonasyombua.dictionarykotlin.domain.repository.DictionaryLocalRepository
import com.madonasyombua.dictionarykotlin.domain.repository.DictionaryRepository
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryLocalUseCase
import com.madonasyombua.dictionarykotlin.domain.usecases.DictionaryUseCase
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModel
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModelFactory
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.applicationContext

/**
 * @author madona
 * Koin is a simple yet powerful Dependency injection framework.
 *
 */
class Dictionary : Application(){

    /*provide/bean/factory: each “provide definition” represents a component to be injected.
    Into the lambda of the “provide definition”, we register a function with the capability
     to create that component.. “bean” and “factory” are extensions of provide(which is now deprecated).
     get(): resolves a component dependency. */

    private val module = applicationContext {
        bean { ApiCreator.get(Api::class.java) }
        bean { CoroutinesContextProvider() }
        bean { DictionaryDataSource(get())}
        bean { DictionaryRepository(get()) }
        bean { DictionaryUseCase(get()) }
        bean { DictionaryViewModelFactory(get(), get(), get()) }

        //build room database
        bean {
            Room.databaseBuilder(androidApplication(), DictionaryDB::class.java, "word_db")
                .build()
        }

        bean { get<DictionaryDB>().getWordDao() }
        bean { DictionaryLocalRepository(get()) }
        bean { DictionaryLocalUseCase(get()) }

        viewModel { DictionaryViewModel(get(), get(), get()) }




    }

    //startKoin: It is the starting point. This method receives a list of “modules”
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(module))
    }

}