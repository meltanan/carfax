package com.example.cars_list.ui.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cars_list.ui.data.dao.Dao
import com.example.cars_list.ui.data.entity.ImageConverter
import com.example.cars_list.ui.data.entity.Vehicle

@Database(entities = [Vehicle::class], version = 2)
@TypeConverters(ImageConverter::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): Dao
}


