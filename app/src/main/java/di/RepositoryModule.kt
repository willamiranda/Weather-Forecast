package di

import data.repository.CurrentWeatherImp
import data.repository.CurrentWeatherrRepository
import data.repository.ForecastRepository
import data.repository.ForecastRepositoryImp
import org.koin.dsl.module
import java.lang.reflect.Array.get

val repositoryModule = module {
    single<CurrentWeatherrRepository> { CurrentWeatherImp(get()) }
    single<ForecastRepository> { ForecastRepositoryImp(get()) }
}