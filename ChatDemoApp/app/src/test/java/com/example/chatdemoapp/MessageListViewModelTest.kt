package com.example.chatdemoapp

import android.app.Application

import com.example.chatdemoapp.ui.model.Message
import com.example.chatdemoapp.ui.model.MessageListUiState
import com.example.chatdemoapp.ui.viewmodel.MessageListViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MessageListViewModelTest {

    @Mock
    lateinit var applicationMock: Application

    @Mock
    lateinit var uiStateFlowMock: MutableStateFlow<MessageListUiState>

    @Mock
    lateinit var uiStateMock: MessageListUiState

    private lateinit var sut: MessageListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        sut = MessageListViewModel(
            applicationMock,
            uiStateFlowMock
        )
        whenever(applicationMock.getString(any())).thenReturn("Testing")
        whenever(uiStateFlowMock.value).thenReturn(uiStateMock)
    }

    @Test
    fun `Should send a message and get a reply back`() {
        runTest {
            // Given
            val message: Message = mock()

            // when
            sut.postMessage(message)
            advanceUntilIdle()

            // then
            verify(uiStateMock, times(2)).addMessageAtBeginning(any())

        }
    }

    @Test
    fun `Should delete a message for given id`() {
        runTest {
            // Given
            val message = Message(
                id = 1.0,
                messageContent = "test",
                isFromMe = true
            )

            // When
            sut.postMessage(message)
            sut.deleteMessage(2.0)


            //then
            //verify(uiStateMock, times(2)).deleteMessage(1.0)
            assert(uiStateMock.messageList.size == 1)

        }
    }
}