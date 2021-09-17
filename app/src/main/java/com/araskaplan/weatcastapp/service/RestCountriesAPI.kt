package com.araskaplan.weatcastapp.service

import com.araskaplan.weatcastapp.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestCountriesAPI {

    @GET("all")
    fun getCountries():Call<List<Country>>
}