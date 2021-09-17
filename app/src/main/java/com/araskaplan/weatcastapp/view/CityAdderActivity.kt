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

    lateinit var countryListString:ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var countryList: ArrayList<Country>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryList= arrayListOf()

        countryListString= arrayListOf()
        arrayAdapter= ArrayAdapter(this@CityAdderActivity,android.R.layout.simple_spinner_item,countryListString).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        }
        main_act_spinner.adapter=arrayAdapter
        getCountries()




    }

    private fun getCountries(){
        WeatherApp.instance.RestCountriesService.getCountries().enqueue(
            object : Callback<List<Country>> {
                override fun onResponse(
                    call: Call<List<Country>>,
                    response: Response<List<Country>>) {
                    response.body()?.let {
                        countryList.addAll(it)
                        for (country in it)  countryListString.add(country.name)
                        arrayAdapter.notifyDataSetChanged()

                    }
                }
                override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                    Toast.makeText(this@CityAdderActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )

    }
}