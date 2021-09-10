package com.araskaplan.weatcastapp.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val weather:List<Weather>,
    val main:Main,
    val visibility:Int,
    val wind:Wind,
    val name:String
)

data class Weather(
    val main: String,
    val description:String,
    val icon:String
)
data class Main(
    val temp:Float,
    val pressure:Float,
    val humidity:Float
)
data class Wind(
    val speed:Float,
    val deg:Int
)
