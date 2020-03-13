package com.madonasyombua.dictionarykotlin.roomtests;


import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.madonasyombua.dictionarykotlin.data.room.DictionaryDB;
import com.madonasyombua.dictionarykotlin.data.room.dao.WordDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class WordDaoTestDB {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private WordDao wordDao;
    private DictionaryDB dictionaryDB;

    // test before database creation
    @Before
    public void dataBaseCreation(){
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        dictionaryDB = Room.inMemoryDatabaseBuilder(context, DictionaryDB.class)
                //this is just allowed here for testing
                .allowMainThreadQueries()
                .build();

        wordDao = dictionaryDB.getWordDao();
    }
    //testing save word for favorite
    @Test
    public void insertItemsOnTheDB() throws InterruptedException {

    }


    // close the database test
    @After
    public void closeTheDatabase(){

        dictionaryDB.close();
    }


}
