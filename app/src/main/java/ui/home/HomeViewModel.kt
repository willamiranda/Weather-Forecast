package ui.home

import android.content.Context
import androidx.compose.ui.semantics.SemanticsProperties.Error
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import base.BaseViewModel
import com.android.volley.NetworkResponse
import com.example.weatherforecast.R
import data.repository.CurrentWeatherrRepository
import data.repository.ForecastRepository
import kotlinx.coroutines.Dispatchers
import data.network.WeaatherrResponse
import utils.hasInternet

class HomeViewModel (
    private val currentWheaterRepository: CurrentWeatherrRepository,
    private val forecastRepository: ForecastRepository
    ) : BaseViewModel(){
    var currentWeather: MutableLiveData<WeaatherrResponse> = MutableLiveData()

    fun getCurrentWeather(context: Context, location: String) = liveData(Dispatchers.IO) {
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
    }