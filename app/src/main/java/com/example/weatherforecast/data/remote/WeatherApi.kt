package com.example.weatherforecast.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/forecast.json?key=360ace76e903406990c144703231008")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("days") days: Int = 5
    ): WeatherDto
}