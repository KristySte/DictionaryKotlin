package com.madonasyombua.dictionarykotlin.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.madonasyombua.dictionarykotlin.R
import com.madonasyombua.dictionarykotlin.data.response.Word
import com.madonasyombua.dictionarykotlin.data.utils.Constants
import com.madonasyombua.dictionarykotlin.databinding.ActivityDefinitionBinding
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModel
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModelFactory
import org.koin.android.ext.android.inject

/**
 * @author Madona
 * Displaying the words more meaning
 */
class DefinitionActivity : AppCompatActivity() {

    lateinit var dictionaryViewModel: DictionaryViewModel
    private val dictionaryViewModelFactory by inject<DictionaryViewModelFactory>()

    private lateinit var word: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDefinitionBinding = DataBindingUtil.setContentView(this, R.layout.activity_definition)
        dictionaryViewModel = ViewModelProviders.of(this, dictionaryViewModelFactory).get(DictionaryViewModel::class.java)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val gson = Gson()
        val wordJsonBody = intent.getStringExtra(Constants.INTENT_WORD_JSON)

        word = gson.fromJson(wordJsonBody, Word::class.java)
        binding.model = word


    }
}
