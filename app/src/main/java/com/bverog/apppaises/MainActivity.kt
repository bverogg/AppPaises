package com.bverog.apppaises

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bverog.apppaises.ui.MainScreen
import com.bverog.apppaises.ui.theme.AppPaisesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPaisesTheme {
                MainScreen()
            }
        }
    }
}



