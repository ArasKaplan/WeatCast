package com.araskaplan.weatcastapp.base

import android.app.Application
import com.araskaplan.weatcastapp.model.CityResponse
import com.araskaplan.weatcastapp.model.CountryPost
import com.araskaplan.weatcastapp.model.SQLiteHelper
import com.araskaplan.weatcastapp.service.OpenWeatherAPI
import com.araskaplan.weatcastapp.service.PostmanCitiesAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApp : Application() {
    private val BASE_URL_OPENWEATHER="https://api.openweathermap.org/data/2.5/"
    private val BASE_URL_POSTMANCITIES="https://countriesnow.space/api/v0.1/"
    var TRCityList= arrayListOf<String>()
    lateinit var sqLiteHelper: SQLiteHelper
    lateinit var PostmanCitiesService:PostmanCitiesAPI
    lateinit var openWeatherService:OpenWeatherAPI

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



        val retrofitPostmanCitiesAPI=Retrofit.Builder()
            .baseUrl(BASE_URL_POSTMANCITIES)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        PostmanCitiesService=retrofitPostmanCitiesAPI.create(PostmanCitiesAPI::class.java)

        sqLiteHelper= SQLiteHelper(baseContext)




        val requestcall=WeatherApp.instance.PostmanCitiesService.pushPost(CountryPost("turkey"))

        requestcall.enqueue(
            object: Callback<CityResponse> {
                override fun onResponse(
                    call: Call<CityResponse>,
                    response: Response<CityResponse>
                ) {
                    response.body()?.let {
                        for (i in it.data.states){
                            TRCityList.add(i.name.removeSuffix(" Province"))
                        }
                    }
                }

                override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                    println("calismadi")
                }
            }
        )


    }

    companion object{
        lateinit var instance:WeatherApp
    }
}

