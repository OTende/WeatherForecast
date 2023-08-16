package com.example.weatherforecast.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.remote.WeatherApi
import com.example.weatherforecast.data.remote.WeatherDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val api: WeatherApi) : ViewModel() {
    val asd: MutableLiveData<WeatherDto> = MutableLiveData()

    fun completePractice() {
        viewModelScope.launch {
            asd.value = api.getWeather("Moscow", 5)
        }
    }
}