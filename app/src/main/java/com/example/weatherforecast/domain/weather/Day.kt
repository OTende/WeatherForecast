package com.example.weatherforecast.domain.weather

import com.google.gson.annotations.SerializedName


data class Day(
    @SerializedName("avghumidity")
    val avgHumidity: Double,
    @SerializedName("avgtemp_c")
    val avgTemp: Double,
    val condition: Condition,
    @SerializedName("maxwind_kph")
    val maxWind: Double
)