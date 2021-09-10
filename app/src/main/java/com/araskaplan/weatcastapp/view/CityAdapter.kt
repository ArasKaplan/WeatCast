package com.araskaplan.weatcastapp.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.model.WeatherResponse
import kotlinx.android.synthetic.main.recycler_card.view.*

class CityAdapter(
    private var items:ArrayList<WeatherResponse>,
    val callback:ListItemSelectedListener
) : RecyclerView.Adapter<CityAdapter.CityHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_card,parent,false)
        return CityHolder(v)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.itemView.card_city_name.text=items[position].name
        holder.itemView.card_forecast.text=items[position].weather[0].description
        holder.itemView.card_temp.text="$items[position].main.temp"
        holder.itemView.rec_card_img.setOnClickListener(){
        callback.onListItemSelected(items.get(position).name)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cityName:TextView=itemView.card_city_name

    }


}