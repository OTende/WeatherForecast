package com.example.weatherforecast.data.weather

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("day")
    val forecast: Day,
)