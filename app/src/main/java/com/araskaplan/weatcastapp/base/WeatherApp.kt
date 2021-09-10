package com.araskaplan.weatcastapp.base

import android.app.Application
import com.araskaplan.weatcastapp.service.OpenWeatherAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApp : Application() {
    private val BASE_URL="https://api.openweathermap.org/data/2.5/"
    lateinit var service:OpenWeatherAPI

    override fun onCreate() {
        super.onCreate()
        instance=this

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()


        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create(OpenWeatherAPI::class.java)

    }
    companion object{
        lateinit var instance:WeatherApp
    }
}

