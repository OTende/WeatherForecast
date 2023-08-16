package com.example.weatherforecast.domain.util

import com.example.weatherforecast.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyProviderImpl @Inject constructor() : ApiKeyProvider {
    override fun getApiKey(): String {
        return BuildConfig.API_KEY
    }
}