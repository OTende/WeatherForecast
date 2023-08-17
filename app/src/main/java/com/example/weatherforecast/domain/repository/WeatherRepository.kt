package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.data.remote.WeatherDto
import com.example.weatherforecast.domain.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(city: String, days: Int = 5, key: String): Resource<WeatherDto>
}