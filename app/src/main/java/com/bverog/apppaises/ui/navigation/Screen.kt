package com.bverog.apppaises.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

// Clase que no se puede heredar ni se pueden generar clases de esa clase
// se utilizan para definir un singleton
sealed class Screen (val route:String, val title:String, val icon: ImageVector) {
    // se centralizan las rutas
    data object Home : Screen("home", "Inicio", Icons.Default.Home)
    data object Add: Screen("add", "Agregar", Icons.Default.Add)

}