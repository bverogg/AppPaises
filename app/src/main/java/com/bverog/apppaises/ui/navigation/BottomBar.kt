package com.bverog.apppaises.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(){
    val blue = Color(0xFF3266a8) // Azul
    BottomAppBar(
        containerColor = blue,
        contentColor = Color.White
    ) {
        Text(text = "",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomBar(){
    BottomBar()
}