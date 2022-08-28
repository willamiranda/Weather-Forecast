package data.repository

class ForecastRepositoryImp {
    private val forecastDao: ForecastDao
    ) : ForecastRepository {

        override suspend fun addForecast(history: History) {
            return forecastDao.addForecast(history)
        }

        override suspend fun getForecast(): List<History> {
            return forecastDao.getForecast()
        }
}