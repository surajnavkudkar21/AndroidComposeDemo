package com.example.chatdemoapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.chatdemoapp.ui.composeview.ChatScreenParentView
import com.example.chatdemoapp.ui.theme.ChatDemoAppTheme

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatDemoAppTheme {
                ChatScreenParentView()
            }
        }
    }
}