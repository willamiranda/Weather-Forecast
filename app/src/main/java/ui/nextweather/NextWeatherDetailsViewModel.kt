package ui.nextweather

import android.content.Context
import androidx.lifecycle.liveData
import base.BaseViewModel
import com.example.weatherforecast.R
import data.repository.CurrentWeatherrRepository
import kotlinx.coroutines.Dispatchers
import utils.NetworkResponse
import utils.hasInternet

class NextWeatherDetailsViewModel(
    private val currentWheaterRepository: CurrentWeatherrRepository,
) : BaseViewModel() {

    fun getNextWeather(context: Context, location: String) = liveData(Dispatchers.IO) {
        emit(NetworkResponse.Loading)
        if (hasInternet(context)) {
            try {
                emit(
                    NetworkResponse.Success(
                        data = currentWheaterRepository.getForecastNextDays(
                            location, HISTORY_DAYS
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(
                    NetworkResponse.Error(
                        exception = exception.message
                            ?: context.getString(R.string.erro_consulta_api)
                    )
                )
            }
        } else
            emit(NetworkResponse.Error(exception = context.getString(R.string.erro_consulta_api)))
    }

    companion object {
        private const val HISTORY_DAYS = 3
    }
}