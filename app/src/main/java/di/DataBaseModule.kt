package di

import androidx.room.Room
import data.db.entity.ForecastDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ForecastDB::class.java,
            "forecast.db"
        ).build()
    }

    single {
        get<ForecastDB>().forecastQuery()
    }
}