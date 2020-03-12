package com.madonasyombua.dictionarykotlin.presentation.ui.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.madonasyombua.dictionarykotlin.R
import com.madonasyombua.dictionarykotlin.data.response.Word
import com.madonasyombua.dictionarykotlin.data.utils.DefinitionDetailIntent
import com.madonasyombua.dictionarykotlin.data.utils.ERROR_STATUS
import com.madonasyombua.dictionarykotlin.data.utils.ErrorHelper
import com.madonasyombua.dictionarykotlin.data.utils.observeWith
import com.madonasyombua.dictionarykotlin.presentation.ui.adapters.DictionaryAdapter
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModel
import com.madonasyombua.dictionarykotlin.presentation.viewmodels.DictionaryViewModelFactory
import kotlinx.android.synthetic.main.activity_dictionary.*
import org.koin.android.ext.android.inject

/**
 * @author Madona
 * The dictionary class will use the adapter hook to display data on the Recycler view
 */

class DictionaryActivity : AppCompatActivity() {

    lateinit var dictionaryViewModel: DictionaryViewModel
    private val dictionaryViewModelFactory by inject<DictionaryViewModelFactory>()

    lateinit var dictionaryAdapter: DictionaryAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary)
        dictionaryViewModel = ViewModelProviders.of(this,dictionaryViewModelFactory ).get(DictionaryViewModel::class.java)
        recycler_word.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        dictionaryAdapter = DictionaryAdapter()
        recycler_word.adapter = dictionaryAdapter


        dictionaryViewModel.progress.observeWith(this, this::onProgress)
        dictionaryViewModel.uiModel.observeWith(this, this:: onSuccess)
        dictionaryViewModel.errorBase.observeWith(this, this::onError)

        search_button.setOnClickListener{
            dictionaryViewModel.getDefinition(word_search.text.toString().trim())

        }


        dictionaryAdapter.setOnClickListener(object : DictionaryAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Word) {
                startActivity(DefinitionDetailIntent(data))
            }


        })
    }

    private fun onSuccess(data: List<Word>){
        display(this)
        dictionaryAdapter.addWord(data)
    }

    private fun display(activity: Activity){
        val methodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        methodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun onProgress(isShown: Boolean){
        progress_search_bar.visibility =
            if(isShown) View.VISIBLE
            else View.GONE
    }

    // Menu Item
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)

    }

    private fun onError(errorHelper: ErrorHelper) = when (errorHelper.errorStatus) {
        ERROR_STATUS.ERR -> {
            Toast.makeText(this, "Sorry try again", Toast.LENGTH_SHORT).show()
        }
        ERROR_STATUS.NETWORK -> {
            Toast.makeText(this, getString(R.string.network_issue), Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show()
        }
    }
}
