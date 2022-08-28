package ui.history

import androidx.lifecycle.MutableLiveData
import data.repository.ForecastRepository
import kotlinx.coroutines.launch

class ForecastHistoryViewModel {
    private val forecastRepository: ForecastRepository
    ) : BaseViewModel() {

        var listHistory: MutableLiveData<List<History>> = MutableLiveData()

        fun getHistory() {
            launch {
                try {
                    val teste = ForecastRepository.getForecast()
                    listHistory.postValue(teste)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
}