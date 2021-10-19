package com.araskaplan.weatcastapp.service

import com.araskaplan.weatcastapp.model.CityResponse
import com.araskaplan.weatcastapp.model.CountryPost
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostmanCitiesAPI {
    @POST("countries/states")
    fun pushPost(
        @Body country: CountryPost
    ): Call<CityResponse>

}