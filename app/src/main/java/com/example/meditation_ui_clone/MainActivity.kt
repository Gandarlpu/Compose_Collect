package com.example.meditation_ui_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.meditation_ui_clone.ui.theme.Meditation_UI_CloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Meditation_UI_CloneTheme {
                HomeScreen()
            }
        }
    }
}
