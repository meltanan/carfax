package com.example.cars_list.ui.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.entity.Vehicle
import com.example.cars_list.ui.repository.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VehicleViewModel() : ViewModel() {

    var vehicles = MutableLiveData<Listings>()

    fun fetchAndLoadVehicles(){
        Api.retrofitService.getVehicles().enqueue(object: Callback<Listings>{
            override fun onResponse(call: Call<Listings>, response: Response<Listings>) {
                if (response.isSuccessful){
                    Log.d("demo", response.body().toString())
                    vehicles.value = response.body()
                }
            }

            override fun onFailure(call: Call<Listings>, t: Throwable) {
                Log.d("demo", t.message.toString())
            }

        })
    }

//     fun fetchAndLoadVehicles2(){
//
//                Api.retrofitService.getVehicles().enqueue(object: Callback<Listings>{
//                    override fun onResponse(call: Call<Listings>, response: Response<Listings>) {
//                        if (response.isSuccessful){
//                            Log.d("demo", response.body().toString())
//                            vehicles.postValue(response)
//                            wow.postValue("Mohed")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Listings>, t: Throwable) {
//                        Log.d("demo", t.message.toString())
//                    }
//
//                })
//
//    }

}