package com.example.weatherforecast.data.remote

import com.example.weatherforecast.domain.weather.Current
import com.example.weatherforecast.domain.weather.ForecastWeather
import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("current")
    val currentWeather: Current,
    @SerializedName("forecast")
    val forecastWeather: ForecastWeather
)