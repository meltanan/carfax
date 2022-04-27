package com.example.cars_list.ui.network.api

import com.example.cars_list.ui.data.entity.Vehicle
import retrofit2.Call
import retrofit2.http.GET

interface API {
    companion object {
        const val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"
    }

    @GET("assignment.json")
    fun getVehicles(): Call<List<Vehicle>>

}