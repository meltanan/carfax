package com.example.cars_list.ui.repository

import androidx.lifecycle.asLiveData
import com.example.cars_list.ui.App
import com.example.cars_list.ui.Roomdb

class VehicleRepository {
    private val dao = Roomdb.getAppDatabase(App.getAppContext())?.vehicleDao()
    val getAllVehicles = dao?.getAllVehicles()?.asLiveData()

}