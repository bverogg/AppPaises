package com.bverog.apppaises.datasources

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto tipo singleton no tiene una clase definida, se pueden generar propiedades
object RetrofitInstance {

    private const val BASE_URL = "http://4.174.188.145:8080/"

    private val gson: Gson = GsonBuilder().setLenient().create()

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    // patrón de diseño, builder
    // reconocer para poder usar e implementar la serialización
    // para mandar llamar un método
    // transmitimos y regresamos json
    //Genera una instancia.
    // Yo voy a acceder a esa URL, voy a utilizar el cliente,
    //con qué quieres serializar (add converter factory)
    // quiero utilizar los métodos de CountryApiService.
    val api:CountryApiService by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CountryApiService::class.java)
    }


}