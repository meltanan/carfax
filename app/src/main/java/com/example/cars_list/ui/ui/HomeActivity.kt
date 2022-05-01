package com.example.cars_list.ui.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cars_list.R
import com.example.cars_list.ui.SelectListener
import com.example.cars_list.ui.addFragment
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), SelectListener {
    private lateinit var viewModel: VehicleViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var mProgressBar: ProgressBar
    private lateinit var progressBarMessage: TextView

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)
        setUpUi()

        viewModel.viewModelScope.launch {
            viewModel.fetchAndLoadVehicles()
        }
        viewModel.getVehicles?.observe(this) {
            recyclerView.adapter = VehicleAdapter(it, this)
            if (it.isNotEmpty()){
                progressBar.visibility = View.GONE
                mProgressBar.visibility = View.GONE
                progressBarMessage.visibility = View.GONE
            }
        }
    }

    private fun setUpUi() {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ if (it) makePhoneCall() }
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        mProgressBar = findViewById(R.id.mProgressBar)
        progressBarMessage = findViewById(R.id.progress_textView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCallClick(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        if (phoneCallPermissionGranted())
            makePhoneCall()
        else
            requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
    }

    override fun onItemSelected(index: Int) {
        viewModel.selectedVehicleIndex = index
        addFragment<VehicleDetailsFragment>(true)
    }

    private fun makePhoneCall(){
        val intent = Intent(Intent.ACTION_CALL)
        val number = "tel: $phoneNumber"
        intent.data = Uri.parse(number)
        startActivity(intent)
    }

    private fun phoneCallPermissionGranted() =  ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

}