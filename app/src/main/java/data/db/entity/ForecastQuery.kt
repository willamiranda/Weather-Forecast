package data.db.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ForecastQuery {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addForecast(forecast: History)

    @Query("select * from history ORDER BY id DESC")
    fun getForecast(): List<History>
}