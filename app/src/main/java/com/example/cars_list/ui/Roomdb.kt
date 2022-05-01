package com.example.cars_list.ui

import android.content.Context
import androidx.room.*
import com.example.cars_list.ui.data.dao.VehicleDao
import com.example.cars_list.ui.data.entity.ImageConverter
import com.example.cars_list.ui.data.entity.Vehicle

@Database(entities = [Vehicle::class], version = 1)
@TypeConverters(ImageConverter::class)
abstract class Roomdb: RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao?

    companion object {
        private var INSTANCE: Roomdb?= null

        fun getAppDatabase(context: Context): Roomdb? {
            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<Roomdb>(
                    context.applicationContext, Roomdb::class.java, "Vehicles_database"
                ).build()
            }
            return INSTANCE
        }
    }
}