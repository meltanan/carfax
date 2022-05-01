package com.example.cars_list.ui.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.cars_list.ui.App
import com.example.cars_list.ui.Roomdb
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.repository.Api
import com.example.cars_list.ui.repository.VehicleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VehicleViewModel : ViewModel() {

   private val dao = Roomdb.getAppDatabase(App.getAppContext())?.vehicleDao()
   private val repository = VehicleRepository()

    var vehicles = MutableLiveData<Listings>()
    var selectedVehicleIndex = 0

    suspend fun fetchAndLoadVehicles() = withContext(Dispatchers.IO){
        Api.retrofitService.getVehicles().enqueue(object: Callback<Listings>{
            override fun onResponse(call: Call<Listings>, response: Response<Listings>) {
                if (response.isSuccessful){
                    vehicles.value = response.body()

                    viewModelScope.launch {
                        response.body()?.listings?.let { dao?.insetVehicles(it) }
                    }
                }
            }

            override fun onFailure(call: Call<Listings>, t: Throwable) {
                Log.e("Error:", t.message.toString())
            }

        })
    }

    val getVehicles = repository.getAllVehicles
}