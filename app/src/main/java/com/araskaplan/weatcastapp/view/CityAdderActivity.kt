package com.araskaplan.weatcastapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.Country
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityAdderActivity : AppCompatActivity() {

    lateinit var countryList:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryList=ArrayList<String>()
        getCountries()




    }

    private fun getCountries(){
        WeatherApp.instance.RestCountriesService.getCountries().enqueue(
            object : Callback<Country> {
                override fun onResponse(
                    call: Call<Country>,
                    response: Response<Country>) {
                    response.body()?.let {
                        countryList.add(it.name)
                    }
                }
                override fun onFailure(call: Call<Country>, t: Throwable) {
                    Toast.makeText(this@CityAdderActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )

    }
}