package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.weather.City
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json?lang=ru")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int = 5,
        @Query("key") key: String
    ): WeatherDto

    @GET("/v1/search.json")
    suspend fun getCities(
        @Query("q") city: String,
        @Query("key") key: String
    ): List<City>
}