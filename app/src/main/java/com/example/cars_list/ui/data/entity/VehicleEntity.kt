package com.example.cars_list.ui.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import org.jetbrains.annotations.NotNull

@Entity(tableName = "vehicles")
data class Vehicle (
    @PrimaryKey val id: String,
    val vin: String,
    val year: Int,
    val make: String,
    val imageCount: Int,
    val images: Image,
    val listPrice: Int,
    val mileage: Int,
    val model: String,
    val currentPrice : Int,
    val trim: String,
    val exteriorColor: String,
    val interiorColor: String,
    val drivetype: String,
    val engine: String,
    val transmission: String,
    val bodytype: String
)

data class Image (
    val baseUrl: String,
    val firstPhoto: FirstPhoto,
    val large: ArrayList<String>,
    val medium: ArrayList<String>,
    val small: ArrayList<String>
)

data class FirstPhoto (
    val large  : String,
    val medium : String,
    val small  : String,
    )

class ImageConverter {

    @TypeConverter
    fun imageToJson(value: Image?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToImage(value: String) = Gson().fromJson(value, Image::class.java)
}



