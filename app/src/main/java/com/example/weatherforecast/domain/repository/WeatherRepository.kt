package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.data.remote.WeatherDto
import com.example.weatherforecast.data.weather.City
import com.example.weatherforecast.domain.util.Resource
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherData(city: String, days: Int = 5, key: String): Resource<WeatherDto>
    suspend fun getCitiesList(city: String, key: String): List<City>
}