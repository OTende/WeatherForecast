package com.example.weatherforecast.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherforecast.data.weather.ForecastDay
import com.example.weatherforecast.databinding.ListWeatherBinding

class WeatherAdapter(private val list: List<ForecastDay>) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ListWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ForecastDay) {
            with(binding) {
                condition.text = item.forecast.condition.text
                humidity.text = item.forecast.avgHumidity.toString()
                temperature.text = String.format("%s°", item.forecast.avgTemp.toString())

                // Смотреть только на анекдот! Я потом обязательно поправлю!
                // Пупа и Лупа пошли получать зарплату. Но в бухгалтерии всё перепутали, и Лупа получил зарплату за Пупу, а Пупа - за Лупу.
                Glide
                    .with(binding.root)
                    .load("https://" + item.forecast.condition.icon.substring(2))
                    .into(conditionImageView)
                windSpeed.text = String.format("%s км/ч", item.forecast.maxWind.toString())
                val dateList = item.date.split("-")
                date.text = String.format("%s.%s", dateList[2], dateList[1])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListWeatherBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}