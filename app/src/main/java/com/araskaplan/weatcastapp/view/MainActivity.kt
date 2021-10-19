package com.araskaplan.weatcastapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recycler_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData(){

        val cityname:String=intent.getStringExtra("cityname")!!

        WeatherApp.instance.openWeatherService.getData(cityname,"metric").enqueue(
            object:Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    var temp= response.body().apply {
                        if (this!!.weather[0].main=="Clear") main_act_image.setImageResource(R.drawable.clear_sky)
                        else if (this!!.weather[0].main=="Clouds") main_act_image.setImageResource(R.drawable.clouds)
                        else if (this!!.weather[0].main=="Drizzle") main_act_image.setImageResource(R.drawable.drizzle)
                        else if (this!!.weather[0].main=="Rain") main_act_image.setImageResource(R.drawable.rain)
                        else if (this!!.weather[0].main=="Snow") main_act_image.setImageResource(R.drawable.snow)
                        else if (this!!.weather[0].main=="Thunderstorm") main_act_image.setImageResource(R.drawable.thunderstorm)

                        main_act_city_name.text="City: $cityname"
                        main_act_text1.text="Temperature: ${this!!.main.temp}"
                        main_act_text2.text="${this!!.weather[0].description}"
                        main_act_text3.text="Wind: ${this!!.wind.speed}"
                        main_act_text4.text= "Humidity: ${ this!!.main.humidity }"
                        main_act_text5.text= "Visibility: ${this!!.visibility}"
                    }

                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                    Toast.makeText(this@MainActivity,t.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        )
    }




}