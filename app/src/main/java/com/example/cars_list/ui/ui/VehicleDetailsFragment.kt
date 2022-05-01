package com.example.cars_list.ui.ui

import android.os.Bundle
import android.util.Log
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
    private lateinit var vehicleDetailsHeaderTextView: TextView
    private lateinit var vehicleDetailsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.vehicles.observe(this){
            it.listings?.get(viewModel.selectedVehicleIndex)?.let { it1 ->
                Glide.with(this).load(it1.images.firstPhoto.medium).into(imageView)
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
        imageView = view.findViewById(R.id.vehicleDetailsImageView)
        vehicleDetailsMainInfoTextView = view.findViewById(R.id.vehicleDetailsMainInfoTextView)
        vehicleDetailsHeaderTextView = view.findViewById(R.id.vehicleDetailsHeaderTextView)
        vehicleDetailsTextView = view.findViewById(R.id.vehicleDetailsTextView)

    }
}