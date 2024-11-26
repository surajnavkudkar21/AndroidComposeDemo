package com.example.chatdemoapp.ui.composeview

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatdemoapp.ui.theme.PrimaryColor
import com.example.chatdemoapp.ui.theme.PrimaryTextColor

/**
 * A center-aligned top app bar with a title.
 *
 * @param title The title to be displayed in the app bar.
 * @param modifier An optional [Modifier] to configure the layout and appearance of the app bar.
 * @param scrollBehavior An optional [TopAppBarScrollBehavior] to control the scrolling behavior of the app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCenterAlignedAppBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PrimaryColor,
            titleContentColor = PrimaryTextColor
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopCenterAlignedAppBarPreview() {
    MaterialTheme {
        TopCenterAlignedAppBar(title = "Chat Demo Preview")
    }
}