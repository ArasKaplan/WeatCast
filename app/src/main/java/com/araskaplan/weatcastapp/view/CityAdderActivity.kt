package com.araskaplan.weatcastapp.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.children
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.Country
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityAdderActivity : AppCompatActivity() {

    lateinit var countryList:ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryList=ArrayList<String>()
        arrayAdapter= ArrayAdapter(this@CityAdderActivity,android.R.layout.simple_spinner_item,countryList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        main_act_spinner.adapter=arrayAdapter
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
                        println("City name: ${it.name}")
                        arrayAdapter.notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<Country>, t: Throwable) {
                    Toast.makeText(this@CityAdderActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )

    }
}