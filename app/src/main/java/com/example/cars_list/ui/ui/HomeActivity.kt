package com.example.cars_list.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_list.R
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.entity.Property
import com.example.cars_list.ui.repository.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private val viewModel: VehicleViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val list = listOf("Mohamed", "yaseen", "Sarah")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = VehicleAdapter(list)

        val wow = viewModel.fetchAndLoadVehicles()

    }
}