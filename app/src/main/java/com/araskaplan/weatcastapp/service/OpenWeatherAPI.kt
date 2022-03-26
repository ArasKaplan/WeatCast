package com.araskaplan.weatcastapp.service


import com.araskaplan.weatcastapp.BuildConfig
import com.araskaplan.weatcastapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET(BuildConfig.apiKey)
    fun getData(@Query("q")query: String,@Query("units")units:String):Call<WeatherResponse>
}