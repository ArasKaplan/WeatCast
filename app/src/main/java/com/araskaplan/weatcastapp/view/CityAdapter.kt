package com.araskaplan.weatcastapp.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import com.araskaplan.weatcastapp.model.WeatherResponse
import kotlinx.android.synthetic.main.recycler_card.view.*
import java.util.*
import kotlin.collections.ArrayList

class CityAdapter(
    private var items:ArrayList<WeatherResponse>,
    val callback:ListItemSelectedListener
) : RecyclerView.Adapter<CityAdapter.CityHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        return CityHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_card,parent,false))
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.itemView.apply {
            card_city_name.text=items[position].name
            card_forecast.text=items[position].weather[0].main.replaceFirstChar{it.uppercase()}
            card_temp.text="${items[position].main.temp} \u2103"
            if (items[position].weather[0].main=="Clear") rec_card_img.setImageResource(R.drawable.clear_sky)
            else if (items[position].weather[0].main=="Clouds") rec_card_img.setImageResource(R.drawable.clouds)
            else if (items[position].weather[0].main=="Drizzle") rec_card_img.setImageResource(R.drawable.drizzle)
            else if (items[position].weather[0].main=="Rain") rec_card_img.setImageResource(R.drawable.rain)
            else if (items[position].weather[0].main=="Snow") rec_card_img.setImageResource(R.drawable.snow)
            else if (items[position].weather[0].main=="Thunderstorm") rec_card_img.setImageResource(R.drawable.thunderstorm)
            rec_card_img.setOnClickListener(){
                callback.onListItemSelected(items[position].name)
            }
        }
        holder.itemView.rec_card_delete.setOnClickListener {
            WeatherApp.instance.sqLiteHelper.removeData(items[position].name)
            items.removeAt(position)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }


}