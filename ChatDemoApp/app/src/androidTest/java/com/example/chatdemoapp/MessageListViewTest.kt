package com.example.chatdemoapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.chatdemoapp.ui.composeview.ChatScreenParentView
import com.example.chatdemoapp.ui.composeview.MessageInputViewTestTag
import com.example.chatdemoapp.ui.composeview.MessageListView
import com.example.chatdemoapp.ui.composeview.MessageListViewTestTag
import com.example.chatdemoapp.ui.composeview.MessageReceivedTestTag
import com.example.chatdemoapp.ui.composeview.MessageSentTestTag
import com.example.chatdemoapp.ui.theme.ChatDemoAppTheme
import kotlinx.coroutines.flow.MutableStateFlow

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class MessageListViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val themeIsDark = MutableStateFlow(false)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ChatDemoAppTheme(darkTheme = themeIsDark.collectAsStateWithLifecycle(false).value) {
                ChatScreenParentView()
            }
        }
    }

    @Test
    fun appLaunch() {
        // Should display Chat screen
        composeTestRule.onNodeWithTag(MessageListViewTestTag).assertIsDisplayed()
    }

    @Test
    fun displaySentMessageOnUi() {
        val testInput = "Sent Test Message"
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performTextInput(testInput)
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performImeAction()
        composeTestRule.onNodeWithTag(MessageSentTestTag).assertIsDisplayed()
    }

    @Test
    fun displayReceivedMessageOnUi() {
        val testInput = "Send Test Message"
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performTextInput(testInput)
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performImeAction()
        Thread.sleep(5000)
        composeTestRule.onNodeWithTag(MessageReceivedTestTag).assertIsDisplayed()
    }

    @Test
    fun emptyMessageShouldNotBeSentAndDisplayedOnUi() {
        val testInput = " "
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performTextInput(testInput)
        composeTestRule.onNodeWithTag(MessageInputViewTestTag).performImeAction()
        Thread.sleep(5000)
        composeTestRule.onNodeWithTag(MessageReceivedTestTag).assertIsNotDisplayed()
    }
}