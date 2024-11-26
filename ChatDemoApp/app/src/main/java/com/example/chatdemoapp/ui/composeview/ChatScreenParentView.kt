package com.example.chatdemoapp.ui.composeview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chatdemoapp.ui.theme.ChatDemoAppTheme
import com.example.chatdemoapp.ui.viewmodel.MessageListViewModel
import com.example.chatdemoapp.R
import com.example.chatdemoapp.ui.model.Message
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Parent view for the chat screen, serving as the entry point.
 *
 * @param modifier Modifier to be applied to the layout.
 * @param messageListViewModel ViewModel for managing the message list state.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreenParentView(
    modifier: Modifier = Modifier,
    messageListViewModel: MessageListViewModel = viewModel(factory = MessageListViewModel.Factory)
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val uiState by messageListViewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyListState()


    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopCenterAlignedAppBar(
                title = stringResource(id = R.string.app_name),
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // View for Message list
            MessageListView(
                messageList = uiState.messageList,
                scrollState = scrollState,
                modifier = Modifier.weight(1f),
                onClick = { message ->
                    messageListViewModel.deleteMessage(message.id)
                }

            )

            // View to input chat message
            MessageInputView { message ->
                messageListViewModel.postMessage(
                    Message(
                        messageContent = message,
                        isFromMe = true
                    )
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenParentViewPreview() {
    ChatDemoAppTheme {
        ChatScreenParentView()
    }
}