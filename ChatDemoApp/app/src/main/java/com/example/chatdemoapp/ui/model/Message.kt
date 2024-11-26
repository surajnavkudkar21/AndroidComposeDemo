package com.example.chatdemoapp.ui.model

import java.text.SimpleDateFormat
import java.util.*

/**
 * Data class representing a chat message.
 *
 * @property messageContent The actual text content of the message.
 * @property isFromMe A boolean value indicating whether the message was sent by the user (`true`) or received (`false`).
 * @property dateTime The timestamp of the message, formatted as "hh:mm" (hours and minutes).
 */
data class Message(
    val messageContent: String,
    val isFromMe: Boolean,
    val dateTime: String = SimpleDateFormat("hh:mm").format(Date()),
    val id : Double = Math.random()
)
