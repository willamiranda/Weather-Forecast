package data.repository

import data.db.entity.ForecastQuery
import data.db.entity.History

class ForecastRepositoryImp (
    private val forecastQuery: ForecastQuery
    ) : ForecastRepository {

        override suspend fun addForecast(history: History) {
            return forecastQuery.addForecast(history)
        }

        override suspend fun getForecast(): List<History> {
            return forecastQuery.getForecast()
        }
}