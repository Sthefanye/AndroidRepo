package com.example.androidrepo


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.androidrepo.presentation.screens.repositories.RepositoriesListActivity
import com.example.androidrepo.presentation.screens.repositories.adapter.RepositoriesListAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@LargeTest
@HiltAndroidTest
class RepositoriesListActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenario = ActivityScenarioRule(RepositoriesListActivity::class.java)

    private val position = 2
    
    @Before
    fun setup() {
        hiltRule.inject()
    }
    @Test
    fun isListIsVisible() {
        onView(withId(R.id.rcListRepositories)).check(matches(isDisplayed()))
    }

    @Test
    fun selectedItemInList() {

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.rcListRepositories)).perform(
            RecyclerViewActions.scrollToPosition<RepositoriesListAdapter.RepositoriesViewHolder>(
                position
            ),
            RecyclerViewActions.actionOnItemAtPosition<RepositoriesListAdapter.RepositoriesViewHolder>(
                position,
                click()
            )
        )
    }
}