package data.repository

import network.ForecastResponse
import network.WeaatherrResponse

interface CurrentWeatherrRepository {
    suspend fun getWeatherLocation(location: String): WeaatherrResponse
    suspend fun getForecastNextDays(location: String, days: Int): ForecastResponse
}