package com.bverog.apppaises.repositories

import com.bverog.apppaises.datasources.RetrofitInstance
import com.bverog.apppaises.model.Country
import retrofit2.Response
import retrofit2.http.Body

class CountryRepository {
    // se crea la instancia directamente de RetrofitInstance
    private val api = RetrofitInstance.api
    // permite que sea asíncrono Regresa una lista de países
    suspend fun getCountries():List<Country>{
        val result = api.getAllCountries()
        return result
    }
    // asíncrono para agregar un país
    suspend fun addCountry(@Body country: Country): Response<Country>{
        val result = api.addCountry(country)
        return result
    }

}