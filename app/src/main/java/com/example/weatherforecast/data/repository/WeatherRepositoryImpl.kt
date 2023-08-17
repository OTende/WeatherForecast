package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.remote.WeatherApi
import com.example.weatherforecast.data.remote.WeatherDto
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.domain.util.Resource
import java.lang.Exception
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {
    override suspend fun getWeatherData(city: String, days: Int, key: String): Resource<WeatherDto> {
        return try {
            Resource.Success(api.getWeather(city, days, key))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Произошла неизвестная ошибка")
        }
    }
}