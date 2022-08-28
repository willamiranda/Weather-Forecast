package data.repository

import data.db.entity.ForecastQuery
import data.db.entity.History

class ForecastRepositoryImp (
    private val forecastDao: ForecastQuery
    ) : ForecastRepository {

        override suspend fun addForecast(history: History) {
            return forecastDao.addForecast(history)
        }

        override suspend fun getForecast(): List<History> {
            return forecastDao.getForecast()
        }
}