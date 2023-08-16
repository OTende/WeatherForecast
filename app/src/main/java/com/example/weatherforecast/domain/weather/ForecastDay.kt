package com.example.weatherforecast.domain.weather

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("day")
    val forecast: Day,
)