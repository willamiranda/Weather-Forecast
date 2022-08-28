package data.network


data class ForecastResponse(
    val forecast : ForecastDay
)

data class ForecastDay(
    val forecastday : List<Forecast>
)

data class Forecast(
    val date: String, val day : Day
)

data class Day(
    val maxtemp_c: Float, val mintemp_c : Float, val condition: Condition
)