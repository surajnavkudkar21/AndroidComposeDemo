package com.example.chatdemoapp.ui.composeview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatdemoapp.ui.model.Message
import com.example.chatdemoapp.ui.theme.PrimaryColor
import com.example.chatdemoapp.ui.theme.PurpleGrey40
import com.example.chatdemoapp.ui.viewmodel.MessageListViewModel

const val MessageListViewTestTag = "MessageListViewTestTag"
const val MessageSentTestTag = "MessageSentTestTag"
const val MessageReceivedTestTag = "MessageReceivedTestTag"

/**
 * This composable function utilizes a LazyColumn to efficiently render a scrollable list of
 * chat messages.
 *
 * @param scrollState The state of the LazyColumn's scroll position.
 * @param messageList The list of messages to be displayed.
 * @param modifier An optional [Modifier] to configure the layout and appearance of the view.
 */
@Composable
fun MessageListView(
    scrollState: LazyListState,
    messageList: List<Message>,
    modifier: Modifier = Modifier,
    onClick: (message:Message) -> Unit
) {
    Box(modifier = modifier.padding(4.dp)) {
        LazyColumn(
            reverseLayout = true,
            modifier = Modifier
                .fillMaxSize()
                .testTag(MessageListViewTestTag),
            state = scrollState,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items = messageList, itemContent = { message ->
                MessageItem(
                    message = message,
                    modifier = modifier,
                    onClick = {
                       onClick(message)
                    }
                )

            })

        }
    }
}

/**
 * Represents an individual message item within the chat list.
 *
 * @param message The message object containing the content and metadata.
 * @param modifier An optional [Modifier] to configure the layout and appearance of the item.
 */
@Composable
fun MessageItem(
    message: Message,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {
    val textAlignment = if (message.isFromMe) Alignment.End else Alignment.Start
    val backgroundColor = if (message.isFromMe) PrimaryColor else PurpleGrey40
    val textColor = Color.White
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .testTag(if (message.isFromMe) MessageSentTestTag else MessageReceivedTestTag),
        horizontalAlignment = textAlignment
    ) {

        MessageBubbleView(
            message = message,
            backgroundColor = backgroundColor,
            textColor = textColor,
            onClick = onClick
        )

        AuthorAndTimeView(
            message = message
        )

    }
}

@Preview
@Composable
fun MessageItemPreview() {
    MessageItem(Message(messageContent = "Test", isFromMe = true), onClick = {

    })
}
