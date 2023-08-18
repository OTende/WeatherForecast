package com.example.weatherforecast.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import javax.inject.Inject

const val CITY_NAME = "City name"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: WeatherApi

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val sharedPreferences get()= getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cityName = sharedPreferences.getString(CITY_NAME, "")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCityEditing()

        if (!cityName.isNullOrEmpty()) {
            viewModel.loadWeather(cityName)
            binding.cityNameTextView.text = cityName
        }

        viewModel.citiesList.observe(this) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, it.map { it.name })
            binding.cityNameEditText.setAdapter(adapter)
        }

        binding.cityNameEditText.addTextChangedListener {
            val text = it.toString()
            if (text.length > 2)
                viewModel.updateCitiesList(it.toString())
        }

        viewModel.state.observe(this) { state ->
            if (!state.isLoading)
                with(binding) {
                    state.weather?.let {
                        currentCondition.text = it.currentWeather.condition.text
                        currentTemperature.text = getString(
                            R.string.temperature,
                            it.forecastWeather.forecastDay[0].forecast.avgTemp.toString()
                        )
                        
                        // Смотреть только на анекдот! Я потом обязательно поправлю!
                        // Пупа и Лупа пошли получать зарплату. Но в бухгалтерии всё перепутали, и Лупа получил зарплату за Пупу, а Пупа - за Лупу.
                        Glide.with(this@MainActivity)
                            .load("https://" + it.currentWeather.condition.icon.substring(2))
                            .into(binding.todayIcon)

                        val adapter = WeatherAdapter(it.forecastWeather.forecastDay)
                        binding.weatherList.layoutManager = LinearLayoutManager(this@MainActivity)
                        binding.weatherList.adapter = adapter
                    }
                }
        }
    }

    private fun setupCityEditing() {
        binding.editCityButton.setOnClickListener {
            if (binding.cityNameEditText.visibility == View.GONE) {
                binding.cityNameEditText.visibility = View.VISIBLE
                binding.cityNameTextView.visibility = View.INVISIBLE
                it.setBackgroundResource(R.drawable.baseline_check_24)
            } else {
                val city = binding.cityNameEditText.text.toString()
                sharedPreferences.edit().putString(CITY_NAME, city).apply()
                binding.cityNameEditText.visibility = View.GONE
                binding.cityNameTextView.text = city
                binding.cityNameTextView.visibility = View.VISIBLE
                viewModel.loadWeather(city)
                it.setBackgroundResource(R.drawable.baseline_edit_24)
            }
        }
    }
}