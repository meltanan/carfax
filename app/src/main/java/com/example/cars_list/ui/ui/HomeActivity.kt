package com.example.cars_list.ui.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_list.R
import com.example.cars_list.ui.SelectListener
import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.entity.Property
import com.example.cars_list.ui.repository.Api
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest

class HomeActivity : AppCompatActivity(), SelectListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
   private var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ if (it) makePhoneCall() }
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = VehicleAdapter(list)

        viewModel.vehicles.observe(this){
            Log.d("demo", it.toString())
            recyclerView.adapter = it.listings?.let { it1 -> VehicleAdapter(it1, this) }

        }

        viewModel.viewModelScope.launch {
             viewModel.fetchAndLoadVehicles()
        }
    }

    override fun onCallClick(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        if (phoneCallPermissionGranted())
            makePhoneCall()
        else
            requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)

    }

    private fun makePhoneCall(){
        val intent = Intent(Intent.ACTION_CALL)
        val number = "tel: +$phoneNumber"
        intent.data = Uri.parse(number)
        startActivity(intent)
    }

    private fun phoneCallPermissionGranted() =  ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED


}