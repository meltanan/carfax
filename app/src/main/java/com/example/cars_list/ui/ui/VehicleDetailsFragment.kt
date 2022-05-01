package com.example.cars_list.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.cars_list.R

class VehicleDetailsFragment : Fragment() {
    private val viewModel: VehicleViewModel by activityViewModels()
    private lateinit var imageView: ImageView
    private lateinit var vehicleDetailsMainInfoTextView: TextView
    private lateinit var priceAndMileageTextView: TextView
    private lateinit var vehicleDetailsHeaderTextView: TextView
    private lateinit var vehicleDetailsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.vehicles.observe(this){
            it.listings?.get(viewModel.selectedVehicleIndex)?.let { it1 ->
                val yearAndOther = "${it1.year} ${it1.make} ${it1.model} ${it1.trim}"
                val priceAndMileage = "$${it1.currentPrice} | ${it1.mileage}k mi"
                val headerInfo = "Location\nExterior Color\nInterior Color\nDrive Type\nTransmission\nBody Style\nEngine\nFuel"
                val details = "${it1.dealer.city}, ${it1.dealer.state}\n${it1.exteriorColor}\n${it1.interiorColor}\n${it1.drivetype}\n${it1.transmission}\n${it1.bodytype}\n${it1.engine}\n${it1.fuel}\n"
                Glide.with(this).load(it1.images.firstPhoto.medium).into(imageView)
                vehicleDetailsMainInfoTextView.text = yearAndOther
                priceAndMileageTextView.text = priceAndMileage
                vehicleDetailsHeaderTextView.text = headerInfo
                vehicleDetailsTextView.text = details

            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.details_image_view)
        vehicleDetailsMainInfoTextView = view.findViewById(R.id.details_year_and_other_text)
        priceAndMileageTextView = view.findViewById(R.id.details_price_and_mileage_text)
        vehicleDetailsHeaderTextView = view.findViewById(R.id.details_header_text)
        vehicleDetailsTextView = view.findViewById(R.id.details_text)

    }
}