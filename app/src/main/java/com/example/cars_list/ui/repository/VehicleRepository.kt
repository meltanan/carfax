package com.example.cars_list.ui.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.cars_list.ui.App
import com.example.cars_list.ui.Roomdb
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.persistence.VehicleDatabase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VehicleRepository () {
    val dao = Roomdb.getAppDatabase(App.getAppContext())?.vehicleDao()

    var vehicles = MutableLiveData<Listings>()
//    suspend fun fetchAndLoadVehicles(){
//        Api.retrofitService.getVehicles().enqueue(object: Callback<Listings> {
//            override fun onResponse(call: Call<Listings>, response: Response<Listings>) {
//                if (response.isSuccessful){
//                    vehicles.value = response.body()
//
//                    viewModelScope.launch {
//                        response.body()?.listings?.let { dao?.insetVehicles(it) }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<Listings>, t: Throwable) {
//                Log.e("Error:", t.message.toString())
//            }
//
//        })
//    }

    fun getData() = dao?.getAllVehicles()?.asLiveData()

}