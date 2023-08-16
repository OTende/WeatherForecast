package com.example.weatherforecast.domain.util

interface ApiKeyProvider {
    fun getApiKey(): String
}