package com.araskaplan.weatcastapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_main.*
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
                        main_act_text1.text=cityname
                        main_act_text2.text="${this!!.main.temp}"
                        main_act_text3.text=this!!.weather[0].description
                        main_act_text4.text= "${ this!!.wind.speed }"
                        main_act_text5.text= this!!.weather[0].main
                        main_act_text6.text= "${ this!!.visibility }"
                    }
                    //Ui islemleri

                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

                    Toast.makeText(this@MainActivity,t.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        )
    }




}