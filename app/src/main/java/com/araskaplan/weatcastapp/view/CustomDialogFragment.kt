package com.araskaplan.weatcastapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.araskaplan.weatcastapp.R
import com.araskaplan.weatcastapp.base.WeatherApp
import kotlinx.android.synthetic.main.fragment_custom_dialog.*
import kotlinx.android.synthetic.main.fragment_custom_dialog.view.*

class CustomDialogFragment:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var rootView: View=inflater.inflate(R.layout.fragment_custom_dialog,container,false)

        var adapter= context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                WeatherApp.instance.TRCityList
            )
        }
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rootView.custom_dialog_fragment_spinner.adapter=adapter

        rootView.custom_dialog_fragment_button.setOnClickListener{
            WeatherApp.instance.apply {
                sqLiteHelper.insertCity(TRCityList[rootView.custom_dialog_fragment_spinner.selectedItemPosition])
            }
            startActivity(Intent(it.context,MainActivity::class.java)
                .putExtra("cityname",WeatherApp.instance.TRCityList[rootView.custom_dialog_fragment_spinner.selectedItemPosition]))
            dismiss()
        }
        return rootView
    }

}