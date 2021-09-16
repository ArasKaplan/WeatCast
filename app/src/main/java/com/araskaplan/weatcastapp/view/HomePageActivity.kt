package com.araskaplan.weatcastapp.view

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.Weather
import com.araskaplan.weatcastapp.model.WeatherResponse
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePageActivity : AppCompatActivity() {
    private lateinit var responseList:ArrayList<WeatherResponse>
    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        recyclerView=findViewById(R.id.recyclerview)

        var cities=ArrayList<String>()
        cities.add("London")
        cities.add("Istanbul")
        cities.add("Chicago")

        responseList= arrayListOf()
        val cityAdapter=CityAdapter(responseList,object :ListItemSelectedListener{
            override fun onListItemSelected(name: String) {
                val intent= Intent(this@HomePageActivity,MainActivity::class.java)
                intent.putExtra("cityname",name)
                startActivity(intent)
            }
        })

        for (city in cities){
            //responseList.add(getData(city))
            getData(city,cityAdapter)
        }
        recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.adapter=cityAdapter
        recyclerView.scheduleLayoutAnimation()

        home_page_fab.setOnClickListener(){
            val intent=Intent(it.context,CityAdderActivity::class.java)
            startActivity(intent)
        }


    }

    private fun getData(city:String, adapter: CityAdapter){
        var temp:WeatherResponse?=null
        WeatherApp.instance.openWeatherService.getData(city,"metric").enqueue(
            object: Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ){
                    response.body()?.let { asd->
                        responseList.add(asd)
                    }
                    adapter.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@HomePageActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}