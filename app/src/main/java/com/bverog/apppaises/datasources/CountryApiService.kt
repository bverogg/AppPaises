package com.bverog.apppaises.datasources
import com.bverog.apppaises.model.Country
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.POST

interface CountryApiService {
    @GET("api/Country")
    suspend fun getAllCountries():List<Country>

    @POST("api/Country")
    suspend fun addCountry(@Body country: Country): Response<Country>

   /* @GET("v3.1/all")
    suspend fun getAllCountries():List<Country>

    // polimormismo o sobrecarga
    @GET("v3.1/all")
    suspend fun getAllCountries(
        // solo se trae esos campos
        @Query("fields") fields:String="name,capital,flags"
    ):List<Country>*/

}