package com.example.cars_list.ui.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.repository.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VehicleViewModel() : ViewModel() {

    fun fetchAndLoadVehicles() {
        Api.retrofitService.getVehicles().enqueue(object: Callback<Listings>{
            override fun onResponse(call: Call<Listings>, response: Response<Listings>) {

                if (response.isSuccessful){
                    Log.d("demo", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Listings>, t: Throwable) {
                Log.d("demo", t.message.toString())
            }

        })
    }

}