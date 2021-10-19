package com.araskaplan.weatcastapp.service


import com.araskaplan.weatcastapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("weather?appid=1a0d7917c1162b9e4833fc1dee2c02c6")
    fun getData(@Query("q")query: String,@Query("units")units:String):Call<WeatherResponse>
}