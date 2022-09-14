package data.network

import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "d99cc470b86349e6980233015221409"

interface Api {

    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") location: String
    ): WeaatherrResponse

    @GET("forecast.json")
    suspend fun getForecastNextDays(
        @Query("q") location: String,  @Query("days") days: Int
    ): ForecastResponse
}