package com.araskaplan.weatcastapp.base

import android.app.Application
import com.araskaplan.weatcastapp.service.CountryStateAPI
import com.araskaplan.weatcastapp.service.OpenWeatherAPI
import com.araskaplan.weatcastapp.service.RestCountriesAPI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.internal.Internal.instance
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONTokener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class WeatherApp : Application() {
    private val BASE_URL_OPENWEATHER="https://api.openweathermap.org/data/2.5/"
    private val BASE_URL_RESTCOUNTRY="https://restcountries.eu/rest/v2/"
    private val BASE_URL_CountryState="https://api.countrystatecity.in/v1/"
    lateinit var CountryStateService:CountryStateAPI
    lateinit var openWeatherService:OpenWeatherAPI
    lateinit var RestCountriesService:RestCountriesAPI

    override fun onCreate() {
        super.onCreate()
        instance=this

        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()


        val retrofitOpenWeatherAPI= Retrofit.Builder()
            .baseUrl(BASE_URL_OPENWEATHER)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        openWeatherService=retrofitOpenWeatherAPI.create(OpenWeatherAPI::class.java)

        val retrofitRestCountriesAPI=Retrofit.Builder()
            .baseUrl(BASE_URL_RESTCOUNTRY)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        RestCountriesService=retrofitRestCountriesAPI.create(RestCountriesAPI::class.java)


        val retrofitCountryStateAPI=Retrofit.Builder()
            .baseUrl(BASE_URL_CountryState)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        CountryStateService=retrofitCountryStateAPI.create(CountryStateAPI::class.java)

    }

    companion object{
        lateinit var instance:WeatherApp
    }
}

