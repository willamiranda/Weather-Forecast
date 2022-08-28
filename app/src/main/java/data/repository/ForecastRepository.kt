package data.repository

interface ForecastRepository {
    suspend fun addForecast(history: History)
    suspend fun getForecast(): List<History>

}