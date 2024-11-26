package com.example.chatdemoapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import com.example.chatdemoapp.R
import com.example.chatdemoapp.ui.model.Message
import com.example.chatdemoapp.ui.model.MessageListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state of the message list in a chat conversation.
 *
 * @param application The Android application context.
 * @param uiState The initial state of the message list UI.
 */
class MessageListViewModel(
    application: Application,
    uiState: MutableStateFlow<MessageListUiState> = MutableStateFlow(MessageListUiState())
) : AndroidViewModel(application) {

    internal val uiState: StateFlow<MessageListUiState> = uiState.asStateFlow()

    /**
     * Posts a new message to the chat conversation.
     *
     * This function adds the given message to the beginning of the message list and
     * simulates a delay before adding a bot response.
     *
     * @param message The [Message] object to be posted.*/
    fun postMessage(message: Message) {
        viewModelScope.launch {
            uiState.value.addMessageAtBeginning(message)
            delay(5000)

                uiState.value.addMessageAtBeginning(
                    Message(
                        messageContent = getApplication<Application>().getString(R.string.reply_bot),
                        isFromMe = false
                    )
                )

        }
    }

    fun deleteMessage(id: Double) {
        viewModelScope.launch {
            uiState.value.deleteMessage(id)
        }
    }

    companion object {
        /**
         * Factory for creating instances of [MessageListViewModel].
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                MessageListViewModel(
                    application = application
                )
            }
        }
    }
}