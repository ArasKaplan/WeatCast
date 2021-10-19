package com.araskaplan.weatcastapp.view

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import java.util.LinkedList

class HomePageActivity : AppCompatActivity() {
    private lateinit var responseList:ArrayList<WeatherResponse>
    private lateinit var myQueue: Queue<String>
    private lateinit var cityAdapter: CityAdapter
    var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)


        myQueue=LinkedList()
        myQueue.addAll(WeatherApp.instance.sqLiteHelper.readData())
        responseList= arrayListOf()


        cityAdapter=CityAdapter(responseList,object :ListItemSelectedListener{
            override fun onListItemSelected(name: String) {
                startActivity(Intent(this@HomePageActivity,MainActivity::class.java).putExtra("cityname",name))
            }
        })

        myQueue.peek()?.let {
            getData(myQueue,cityAdapter)
        }

        recyclerview.apply {
            layoutManager=LinearLayoutManager(this@HomePageActivity)
            adapter=cityAdapter
            scheduleLayoutAnimation()
        }

        home_page_fab.setOnClickListener{
            var dialog= CustomDialogFragment()
            dialog.show(supportFragmentManager,"customDialog")
        }


    }



    private fun getData(city:String, adapter: CityAdapter){
        WeatherApp.instance.openWeatherService.getData(city,"metric").enqueue(
            object: Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ){
                    response.body()?.let { asd->
                        asd.name.removeSuffix(" Province")
                        responseList.add(asd)
                        adapter.notifyDataSetChanged()
                        myQueue.poll()?.apply {
                            getData(this,adapter)
                        }
                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@HomePageActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )
    }
    //Overload GetData
    private fun getData(queue:Queue<String>,adapter: CityAdapter){
        WeatherApp.instance.openWeatherService.getData(queue.remove(),"metric").enqueue(
            object: Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    response.body()?.let {
                        val temp=WeatherResponse(
                            name =it.name.removeSuffix(" Province"),
                            weather = it.weather,
                            main = it.main,
                            visibility = it.visibility,
                            wind = it.wind
                        )
                        responseList.add(temp)
                        adapter.notifyDataSetChanged()
                        queue.peek()?.apply {
                            getData(queue,adapter)
                        }
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Toast.makeText(this@HomePageActivity,t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        if (count==0) count++
        else{
            myQueue.clear()
            responseList.clear()

            myQueue.addAll(WeatherApp.instance.sqLiteHelper.readData())
            myQueue.peek()?.let {
                getData(myQueue,cityAdapter)
            }
            println("deneme")
        }
    }

}