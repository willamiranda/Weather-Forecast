package di


import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ui.history.ForecastHistoryViewModel
import ui.home.HomeViewModel
import ui.nextweather.NextWeatherDetailsViewModel

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { NextWeatherDetailsViewModel(get()) }
    viewModel { ForecastHistoryViewModel(get()) }
}
