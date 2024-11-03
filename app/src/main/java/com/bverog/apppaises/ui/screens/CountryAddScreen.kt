package com.bverog.apppaises.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bverog.apppaises.model.Country
import com.bverog.apppaises.ui.viewmodels.CountryAddUiState
import com.bverog.apppaises.ui.viewmodels.CountryViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect


@Composable
fun CountryAddScreen(innerPadding: PaddingValues,
                     navController: NavController,
                     countryViewModel: CountryViewModel = viewModel()
){
    // se declara el uiState desde el countryViewModel
    val addUiState by countryViewModel.addUiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var capital by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    // Variable para mostrar el mensaje de error
    var errorMessage by remember { mutableStateOf("") }


    val Blue = Color(0xFF3266a8) // Azul

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("home") // Cambia esto por la ruta deseada
                // Acción a realizar cuando se hace clic en el botón
            },
                //containerColor = Color.DarkGray ,
                containerColor = Blue,
                contentColor = Color.White// Color azul marino
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                //
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(paddingValues), // Usa paddingValues para evitar solapamiento
            //contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Captura los datos del país ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3266a8),
                    modifier = Modifier.fillMaxWidth().padding(18.dp)
                )

                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 20.dp) // Establecer el ancho del TextField
                )
                TextField(
                    value = capital, // Usar String como valor
                    onValueChange = {
                        capital = it
                    },
                    label = { Text("Capital") },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 30.dp) // Establecer el ancho del TextField
                )
                TextField(
                    value = imageUrl, // Usar String como valor
                    onValueChange = {
                        imageUrl = it
                    },
                    label = { Text("Url de la Bandera") },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 30.dp) // Establecer el ancho del TextField
                )
                Button(onClick = {
                    // Validar que los campos no estén vacíos
                    if (name.isBlank() || capital.isBlank() || imageUrl.isBlank()) {
                        errorMessage = "Todos los campos son obligatorios."
                    } else {
                        val newCountry = Country(name = name, capital = capital, image = imageUrl)
                        countryViewModel.onAddCountry(newCountry) // Llama al método para agregar el país

                    }


                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3266a8),
                        contentColor = Color.White // Color del texto o ícono en el botón
                    ),
                    modifier = Modifier.fillMaxWidth()
                    .padding(top = 30.dp)) {
                    Text("Guardar")

                }

                // Muestra el mensaje de error si existe
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
                }


                // Observa el estado de adición y muestra el contenido correspondiente
                when (addUiState) {
                    is CountryAddUiState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    is CountryAddUiState.Success -> {
                        Text("País agregado correctamente", color = Color.Green)
                        LaunchedEffect(Unit) {
                            navController.popBackStack() // Navega de regreso tras agregar el país
                        }
                    }
                    is CountryAddUiState.Error -> {
                        val message = (addUiState as CountryAddUiState.Error).message
                        Text(text = message, color = Color.Red)
                    }
                    else -> {}
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewCountryAddScreen() {
 // Proporcionar valores de padding
 CountryAddScreen(innerPadding = PaddingValues(16.dp), navController = rememberNavController())
}