package com.example.weatherforecast.presentation

import com.example.weatherforecast.data.remote.WeatherDto

data class WeatherState(
    val city: String? = null,
    val weather: WeatherDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
