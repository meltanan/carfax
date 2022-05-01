package com.example.cars_list.ui.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.cars_list.R
import com.example.cars_list.ui.data.entity.Vehicle

class VehicleDetailsFragment : Fragment() {
    private val viewModel: VehicleViewModel by activityViewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var imageView: ImageView
    private lateinit var yearAndOtherTextView: TextView
    private lateinit var priceAndMileageTextView: TextView
    private lateinit var headerTextView: TextView
    private lateinit var detailsTextView: TextView
    private lateinit var button: Button
    private var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getVehicles?.observe(this){
            it.get(viewModel.selectedVehicleIndex).let { vehicle ->
                setUpUi(vehicle)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ if (it) makePhoneCall() }
        return inflater.inflate(R.layout.fragment_vehicle_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.details_image_view)
        yearAndOtherTextView = view.findViewById(R.id.details_year_and_other_text)
        priceAndMileageTextView = view.findViewById(R.id.details_price_and_mileage_text)
        headerTextView = view.findViewById(R.id.details_header_text)
        detailsTextView = view.findViewById(R.id.details_text)
        button = view.findViewById(R.id.details_call_dealer_button)

    }

    private fun setUpUi(vehicle: Vehicle) {
        val yearAndOther = "${vehicle.year} ${vehicle.make} ${vehicle.model} ${vehicle.trim}"
        val priceAndMileage = "$${vehicle.currentPrice} | ${vehicle.mileage}k mi"
        val headerInfo = "Location\nExterior Color\nInterior Color\nDrive Type\nTransmission\nBody Style\nEngine\nFuel"
        val details = "${vehicle.dealer.city}, ${vehicle.dealer.state}\n${vehicle.exteriorColor}\n${vehicle.interiorColor}\n${vehicle.drivetype}\n${vehicle.transmission}\n${vehicle.bodytype}\n${vehicle.engine}\n${vehicle.fuel}\n"

        Glide.with(this).load(vehicle.images.firstPhoto.medium).into(imageView)
        this.yearAndOtherTextView.text = yearAndOther
        priceAndMileageTextView.text = priceAndMileage
        headerTextView.text = headerInfo
        detailsTextView.text = details
        phoneNumber = vehicle.dealer.phone
        button.setOnClickListener {
            if (phoneCallPermissionGranted())
                makePhoneCall()
            else
                requestPermissionLauncher.launch(android.Manifest.permission.CALL_PHONE)
        }
    }
    private fun makePhoneCall(){
        val intent = Intent(Intent.ACTION_CALL)
        val number = "tel: +$phoneNumber"
        intent.data = Uri.parse(number)
        startActivity(intent)
    }
    private fun phoneCallPermissionGranted() =  ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED

}