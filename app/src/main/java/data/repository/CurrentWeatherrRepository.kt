package data.repository

import data.network.ForecastResponse
import data.network.WeaatherrResponse

interface CurrentWeatherrRepository {
    suspend fun getWeatherLocation(location: String): WeaatherrResponse
    suspend fun getForecastNextDays(location: String, days: Int): ForecastResponse
}