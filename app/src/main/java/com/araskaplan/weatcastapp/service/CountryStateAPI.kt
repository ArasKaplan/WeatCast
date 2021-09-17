package com.araskaplan.weatcastapp.service

import com.araskaplan.weatcastapp.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryStateAPI {



    @Headers("X-CSCAPI-KEY","API_KEY")//apikey->statik
    @GET("countries/{ciso}/cities")
    fun getCities(
        @Path(
            value = "ciso",
            encoded = true
        ) ciso: String
    ): Call<Country>?//TODO:country->City


    //@Headers("Cache-Control: max-age=640000")


}