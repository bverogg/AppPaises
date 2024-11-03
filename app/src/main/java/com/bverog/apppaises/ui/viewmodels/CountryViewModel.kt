package com.bverog.apppaises.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bverog.apppaises.model.Country
import com.bverog.apppaises.repositories.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CountryUiState{
    object Loading:CountryUiState()
    data class Success ( val countries:List<Country>) : CountryUiState()
    data class Error(val message:String):CountryUiState()

}

sealed class CountryAddUiState {
    object Idle : CountryAddUiState()
    object Loading : CountryAddUiState()
    object Success : CountryAddUiState()
    data class Error(val message: String) : CountryAddUiState()
}

// si no se pasa el parámetro, se instancia CountryRepository
class CountryViewModel (private val repository: CountryRepository = CountryRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow<CountryUiState>(CountryUiState.Loading)

    // en el state se guarda si se está conectando y la información que se traerá
    // para que pueda ser accedida externamente
    val uiState: StateFlow<CountryUiState> = _uiState

    private val _addUiState = MutableStateFlow<CountryAddUiState>(CountryAddUiState.Idle)
    val addUiState: StateFlow<CountryAddUiState> = _addUiState


    // inicializador de la instancia
    // en cuanto abra, se traiga los países.
    init{
        fetchCountries()
    }

    // método para traer la información
    private fun fetchCountries(){
        // para notificar
        viewModelScope.launch {
            try{
                _uiState.value = CountryUiState.Loading
                val countries = repository.getCountries()
                _uiState.value = CountryUiState.Success(countries)
            }catch(e:Exception){
                _uiState.value = CountryUiState.Error(e.localizedMessage ?:
                "An unexpected error occurred")
            }
        }
    }

    // función pública para llamarse desde el composable para recargar los países
    fun reloadCountries(){
        fetchCountries()
    }


    // Método para agregar un nuevo país
    private fun addCountry(country: Country) {
        viewModelScope.launch {
            _addUiState.value = CountryAddUiState.Loading // Cambia el estado a Cargando
            try {
                repository.addCountry(country) // Llama al método del repositorio para agregar el país
                fetchCountries() // Actualiza la lista después de la inserción
                _addUiState.value = CountryAddUiState.Success // Cambia el estado a Éxito

            } catch (e: Exception) {
                _addUiState.value = CountryAddUiState.Error(e.localizedMessage ?: "An unexpected error occurred") // Maneja el error
            }
        }
    }

    // Llama a este método desde el Composable cuando quieras agregar un país
    fun onAddCountry(country: Country) {
        addCountry(country)
    }


}