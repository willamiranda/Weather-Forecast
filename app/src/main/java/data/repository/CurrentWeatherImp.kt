package data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import network.Api
import network.ForecastResponse
import network.WeaatherrResponse

class CurrentWeatherImp(    private val api: Api
) : CurrentWeatherrRepository {

    override suspend fun getWeatherLocation(location: String): WeaatherrResponse {
        return withContext(Dispatchers.IO) {
            return@withContext api.getCurrentWeather(location)
        }
    }
    override suspend fun getForecastNextDays(location: String, days : Int): ForecastResponse {
        return withContext(Dispatchers.IO) {
            return@withContext api.getForecastNextDays(location, days)
        }
    }
}