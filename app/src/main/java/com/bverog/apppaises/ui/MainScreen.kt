package com.bverog.apppaises.ui


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bverog.apppaises.ui.navigation.BottomBar
import com.bverog.apppaises.ui.navigation.Screen
import com.bverog.apppaises.ui.navigation.TopBar
import com.bverog.apppaises.ui.screens.CountryAddScreen
import com.bverog.apppaises.ui.screens.CountryListHomeScreen


@Composable
fun MainScreen(){
   val navController = rememberNavController()
   // Estado para el título
   val title = remember { mutableStateOf("Lista de Países") }

   Scaffold (
      //topBar = { TopBar(title = "Inicio")}
      topBar = { TopBar(title = title.value) }, // Usa el estado como título
      bottomBar = { BottomBar()},

   ){ innerPadding ->
      NavHost(
         navController = navController,
         startDestination = Screen.Home.route,
         modifier =  Modifier.padding(innerPadding)
      ){
         composable(Screen.Home.route){
            title.value = "Lista de Países"
            CountryListHomeScreen(innerPadding, navController)
         }
         composable(Screen.Add.route){
            title.value = "Agregar País"
            CountryAddScreen(innerPadding, navController)
         }
      }
   }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar(){
   MainScreen()
}

