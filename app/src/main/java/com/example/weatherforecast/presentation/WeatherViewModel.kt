package com.example.weatherforecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.remote.WeatherApi
import com.example.weatherforecast.data.remote.WeatherDto
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.domain.util.ApiKeyProviderImpl
import com.example.weatherforecast.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val apiKeyProvider: ApiKeyProviderImpl,
) : ViewModel() {

    val asd: MutableLiveData<Resource<WeatherDto>> = MutableLiveData()
    fun loadWeather() {
        viewModelScope.launch {
            asd.value = repository.getWeatherData("Moscow", 5, apiKeyProvider.getApiKey())
        }
    }
}