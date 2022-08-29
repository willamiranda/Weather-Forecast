package data.network

import com.google.gson.annotations.SerializedName


data class CurrentWeatherEntry(


    @SerializedName("temp_c")
    val temp_c: Float,



    @SerializedName("condition")
    val condition: Condition,



)

data class Condition(
    val text: String,
    val icon: String
)