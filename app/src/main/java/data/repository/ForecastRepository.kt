package data.repository

import data.db.entity.History

interface ForecastRepository {
    suspend fun addForecast(history: History)
    suspend fun getForecast(): List<History>

}