package com.example.cars_list.ui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_list.R
import com.example.cars_list.ui.network.api.ServiceGenerator

class HomeActivity : AppCompatActivity() {
    private val viewModel: VehicleViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val wow = ServiceGenerator


//        viewModel.getVehicles.observe(this){
//            recyclerView.adapter = it.data?.let { it1 -> VehicleAdapter(it1) }
//            Log.d("demo", it.toString())
//        }
//        viewModel.getVehicles

    }
}