package com.houlis.haris.picfind.feature.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.houlis.haris.picfind.feature.list.ui.PicturesScreen
import com.houlis.haris.picfind.feature.list.ui.viewmodel.PicturesState
import org.junit.Rule
import org.junit.Test

class PicturesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun launch(state: PicturesState = PicturesState()) {
        composeTestRule.setContent {
            PicturesScreen(state = state, onValueChange = {}, onClick = {})
        }
    }

    @Test
    fun prompts_user_to_search() {
        launch()

        composeTestRule
            .onNodeWithText("Go search for sth!")
            .assertIsDisplayed()
    }

    @Test
    fun shows_no_results_were_found() {
//        launch(PicturesState("", Empty))

        composeTestRule
            .onNodeWithText("No items were found. Please try again.")
            .assertIsDisplayed()
    }

    @Test
    fun shows_error() {
//        launch(PicturesState("", Error))

        composeTestRule
            .onNodeWithText("Sth went wrong, please try again later.")
            .assertIsDisplayed()
    }
}
