package com.example.weatherforecast.data.weather

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    val date: String,
    @SerializedName("day")
    val forecast: Day,
)