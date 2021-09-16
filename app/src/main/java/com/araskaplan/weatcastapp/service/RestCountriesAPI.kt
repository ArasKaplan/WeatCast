package com.araskaplan.weatcastapp.service

import com.araskaplan.weatcastapp.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface RestCountriesAPI {

    @GET("all")
    fun getCountries():Call<List<Country>>
}