package com.example.weatherforecast.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json?lang=ru")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int = 5,
        @Query("key") key: String
    ): WeatherDto
}