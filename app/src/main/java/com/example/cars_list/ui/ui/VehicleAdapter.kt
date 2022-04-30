package com.example.cars_list.ui.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cars_list.R
import com.example.cars_list.ui.data.entity.Vehicle

class VehicleAdapter(private val vehicleList: List<Vehicle>): RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return ViewHolder(v)
    }
    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(vehicleList[position].images.medium).into(holder.imageView)
        holder.detailsTextView.text = vehicleList[position].make
    }


    override fun getItemCount() = vehicleList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var detailsTextView: TextView = itemView.findViewById(R.id.vehicleInfoTextView)
        var button: Button = itemView.findViewById(R.id.button)

    }


}