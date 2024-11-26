package com.example.chatdemoapp.ui.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf

/**
 * Represents the UI state for a list of chat messages.
 *
 */
class MessageListUiState {

    private val _messageList = mutableStateListOf<Message>()
    val messageList: List<Message> = _messageList

    /**
     * Adds a new message to the beginning of the message list.
     */
    fun addMessageAtBeginning(message: Message) {
        addMessage(0, message)
    }

    /**
     * Adds a new message to the message list at the specified index.
     */
    fun addMessage(index: Int, message: Message) {
        _messageList.add(index, message)
        Log.v("MessageListUiState add case", ""+_messageList.size)
    }

    fun deleteMessage(id: Double) {
        _messageList.removeIf { it.id == id }
        Log.v("MessageListUiState delete case", ""+_messageList.size)
    }

}

