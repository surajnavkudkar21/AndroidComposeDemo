package com.example.chatdemoapp.ui.composeview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.chatdemoapp.ui.model.Message
import com.example.chatdemoapp.ui.theme.PrimaryColor

/**
 * Represents the visual representation of a chat message bubble.
 *
 * @param modifier An optional [Modifier] to configure the layout and appearance of the bubble.
 * @param message The [Message] object containing the content to be displayed.
 * @param backgroundColor The background color of the message bubble. Defaults to [PrimaryColor].
 * @param textColor The color of the message text.
 */
@Composable
fun MessageBubbleView(
    modifier: Modifier = Modifier,
    message: Message,
    backgroundColor: Color = PrimaryColor,
    textColor: Color,
    onClick: () -> Unit
) {

    val bubbleShape = RoundedCornerShape(
        topStart = 48f,
        topEnd = 48f,
        bottomStart = if (message.isFromMe) 48f else 0f,
        bottomEnd = if (message.isFromMe) 0f else 48f
    )
    Box(
        modifier = modifier
            .clip(
                bubbleShape
            )
            .background(backgroundColor)
            .padding(16.dp)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = message.messageContent,
            style = MaterialTheme.typography.titleMedium,
            color = textColor
        )
    }
}