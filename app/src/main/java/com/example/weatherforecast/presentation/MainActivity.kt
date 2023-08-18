package com.example.weatherforecast.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherforecast.R
import com.example.weatherforecast.adapters.WeatherAdapter
import com.example.weatherforecast.data.remote.WeatherApi
import com.example.weatherforecast.data.weather.Condition
import com.example.weatherforecast.data.weather.Day
import com.example.weatherforecast.data.weather.ForecastDay
import com.example.weatherforecast.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: WeatherApi

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadWeather()
        viewModel.state.observe(this) { state ->
            if (!state.isLoading)
            with(binding) {
                state.weather?.let {
                    currentCondition.text = it.currentWeather.condition.text
                    currentTemperature.text = getString(
                        R.string.temperature,
                        it.forecastWeather.forecastDay[0].forecast.avgTemp.toString()
                    )
                    val adapter = WeatherAdapter(it.forecastWeather.forecastDay)
                    binding.weatherList.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.weatherList.adapter = adapter
                }
            }
        }
    }
}