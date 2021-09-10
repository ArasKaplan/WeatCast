package com.araskaplan.weatcastapp.service


import com.araskaplan.weatcastapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    //api.openweathermap.org/data/2.5/appid
    // @GET("weather?appid=fe5464f277461029b6c0b84c8b8d193d&units=metric")
    //    fun getWeather(@Query("q") query: String): Call<WeatherResponse>

    @GET("weather?appid=1a0d7917c1162b9e4833fc1dee2c02c6")
    fun getData(@Query("q")query: String,@Query("units")units:String):Call<WeatherResponse>

}