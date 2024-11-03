package com.bverog.apppaises.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.bverog.apppaises.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title:String){
    val blue = Color(0xFF3266a8) // Azul
    TopAppBar( title = { Text(title) },
        colors = topAppBarColors(
            //containerColor = Purple40,
            containerColor = blue,
            titleContentColor =  Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar(){
    TopBar("Inicio")
}