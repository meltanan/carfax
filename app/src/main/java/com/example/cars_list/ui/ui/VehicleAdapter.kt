package com.example.cars_list.ui.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cars_list.R
import com.example.cars_list.ui.SelectListener
import com.example.cars_list.ui.data.entity.Vehicle

class VehicleAdapter(private val vehicleList: List<Vehicle>, private val listener: SelectListener): RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vehicle_item, parent, false)
        return ViewHolder(v)
    }
    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(vehicleList[position].images.firstPhoto.medium).into(holder.imageView)
        val yearAndOtehr = "${vehicleList[position].year} ${vehicleList[position].make} ${vehicleList[position].model} ${vehicleList[position].trim}"
        val details = "\n$${vehicleList[position].currentPrice} | ${vehicleList[position].mileage} k mi" + "\n${vehicleList[position].dealer.city}, ${vehicleList[position].dealer.state}"

        holder.yearAndOtherTextView.text = yearAndOtehr
        holder.priceAndOtherTextView.text = details
        holder.button.setOnClickListener {
            listener.onCallClick(vehicleList[position].dealer.phone)
        }

        holder.cardView.setOnClickListener {
            listener.onItemSelected(position)
        }
    }

    override fun getItemCount() = vehicleList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cardView: CardView = itemView.findViewById(R.id.cardView)
        var imageView: ImageView = itemView.findViewById(R.id.imageView)
        var yearAndOtherTextView: TextView = itemView.findViewById(R.id.year_and_other_text)
        var priceAndOtherTextView: TextView = itemView.findViewById(R.id.price_and_other_text)
        var button: Button = itemView.findViewById(R.id.call_dealer_button)

    }
}