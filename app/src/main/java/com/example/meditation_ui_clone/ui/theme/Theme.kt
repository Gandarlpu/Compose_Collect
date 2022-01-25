package com.example.meditation_ui_clone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable


@Composable
fun Meditation_UI_CloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {


    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}