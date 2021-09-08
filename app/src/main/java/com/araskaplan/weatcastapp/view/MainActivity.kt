package com.araskaplan.weatcastapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.araskaplan.weatcastapp.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL="api.openweathermap.org/data/2.5/weather?q="
    private val APIKEY="1a0d7917c1162b9e4833fc1dee2c02c6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    private fun getData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }




}