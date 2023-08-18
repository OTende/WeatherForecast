package com.example.weatherforecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.weather.City
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.domain.util.ApiKeyProviderImpl
import com.example.weatherforecast.domain.util.Resource
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val apiKeyProvider: ApiKeyProviderImpl,
) : ViewModel() {
    private var _state: MutableLiveData<WeatherState> = MutableLiveData(WeatherState())
    val state: LiveData<WeatherState> = _state

    private var _citiesList: MutableLiveData<List<City>> = MutableLiveData()
    val citiesList: LiveData<List<City>> = _citiesList

    fun updateCitiesList(
        city: String,
        key: String = apiKeyProvider.getApiKey()
    ) {
        viewModelScope.launch {
            _citiesList.postValue(repository.getCitiesList(city, key))
        }
    }

    fun loadWeather(city: String, days: Int = 5, key: String = apiKeyProvider.getApiKey()) {
        viewModelScope.launch {
            val value = when (val result = repository.getWeatherData(city, days, key)) {
                is Resource.Success -> _state.value?.copy(
                    city = city,
                    weather = result.data,
                    isLoading = false
                )

                is Resource.Error -> _state.value?.copy(
                    city = null,
                    weather = null,
                    isLoading = false,
                    error = result.message
                )
            }
            _state.postValue(value)
        }
    }
}