package ui.home

import android.content.Context
import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import base.BaseViewModel
import com.example.weatherforecast.R
import data.db.entity.History
import data.repository.CurrentWeatherrRepository
import data.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import data.network.WeaatherrResponse
import utils.hasInternet
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import utils.NetworkResponse

class HomeViewModel(
    private val currentWheaterRepository: CurrentWeatherrRepository,
    private val forecastRepository: ForecastRepository
) : BaseViewModel() {

    var currentWeather: MutableLiveData<WeaatherrResponse> = MutableLiveData<WeaatherrResponse>()

    fun getCurrentWeather(context: Context, location: String) = liveData(IO) {
        emit(NetworkResponse.Loading)
        if (hasInternet(context)) {
            try {
                emit(
                    NetworkResponse.Success(
                        data = currentWheaterRepository.getWeatherLocation(
                            location
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

    fun addHistory(history: History) {
        launch {
            try {
                forecastRepository.addForecast(history)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}