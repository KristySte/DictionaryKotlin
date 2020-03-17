package com.madonasyombua.dictionarykotlin.uitests;


import androidx.lifecycle.MediatorLiveData;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.google.android.material.textfield.TextInputEditText;
import com.madonasyombua.dictionarykotlin.R;
import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity;

import com.madonasyombua.dictionarykotlin.presentation.ui.activities.DictionaryActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class ViewTests {

    @Rule
    public ActivityTestRule<DictionaryActivity> activityTestRule =
            new ActivityTestRule<>(DictionaryActivity.class, true, true);


    private MediatorLiveData<List<WordEntity>> result = new MediatorLiveData<>();

    @Before
    public void init(){
        //TODO Add Code


    }

    @Test
    public void testSearchWord(){
        //the search button when clicked
        onView(withId(R.id.search_button)).perform(click());

        // type the test in the search field and submit a query
        onView(isAssignableFrom(TextInputEditText.class)).perform(typeText("Smiley"), pressImeActionButton());

        // verify the view model

        // check the progress bar is displayed
        onView(withId(R.id.progress_search_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoadResults(){

    }

    @Test
    public void testWordItemClick(){
        //Verify that items are displayed


        //Verify that the progress bar is shown
        onView(withId(R.id.progress_search_bar)).check(matches(not(isDisplayed())));

        // Click on the first item on the recyclerview


    }

    @Test
    public void testShowError(){

    }
}
