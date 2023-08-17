package com.example.weatherforecast.data.weather

import com.google.gson.annotations.SerializedName

data class ForecastWeather(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)