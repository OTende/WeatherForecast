package com.example.weatherforecast.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast.R
import com.example.weatherforecast.data.remote.WeatherApi
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auto = findViewById<AutoCompleteTextView>(R.id.autotextview)

        viewModel.asd.observe(this) {
            auto.hint = it.toString()
        }

        findViewById<TextView>(R.id.for_view_model).setOnClickListener {
            viewModel.completePractice()
        }
//        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, cityList)
//        auto.setAdapter(adapter)
    }
}