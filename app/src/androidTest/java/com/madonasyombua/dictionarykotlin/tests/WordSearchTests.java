package com.madonasyombua.dictionarykotlin.tests;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.madonasyombua.dictionarykotlin.data.services.Api;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;


@RunWith(AndroidJUnit4.class)
public class WordSearchTests {
    @Rule
    public InstantTaskExecutorRule taskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Api api;


    @Before
    public void testSetUp(){

    }

    @Test
    public void testFetchDataSuccess(){

    }

    @Test
    public void whenNull(){

    }

    @Test
    public void confirmWhenDataHasError(){

    }

    @After
    public void closeTests(){
        
    }
}
