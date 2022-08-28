package data.db.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class ForecastDB : RoomDatabase() {
    abstract fun forecastQuery(): ForecastQuery
}
