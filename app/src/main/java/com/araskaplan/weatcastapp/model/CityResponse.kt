package com.araskaplan.weatcastapp.model

data class CityResponse(
    val data:CountryProps
)
data class CountryProps(
    val states:List<StateProps>
)
data class StateProps(
    val name:String
)

data class CountryPost(
    val country:String
)
