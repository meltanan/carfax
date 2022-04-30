package com.example.cars_list.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_list.R
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.entity.Property
import com.example.cars_list.ui.repository.Api
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list = listOf("Mohamed", "yaseen", "Sarah")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = VehicleAdapter(list)

        viewModel.vehicles.observe(this){
            Log.d("demo", it.toString())
            recyclerView.adapter = it.listings?.let { it1 -> VehicleAdapter(it1) }


        }

        viewModel.viewModelScope.launch {
            val wow = viewModel.fetchAndLoadVehicles()
            Log.d("demo2", wow.toString())

        }
    }
}