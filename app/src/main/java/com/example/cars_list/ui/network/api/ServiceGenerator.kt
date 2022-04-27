package com.example.cars_list.ui.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {

    private val client = OkHttpClient.Builder().build()
    private val retofit = Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun <T> buildSerive(service: Class<T>): T{
        return retofit.create(service)
    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit =
//        Retrofit.Builder()
//            .baseUrl(API.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideApi(retrofit: Retrofit): API =
//        retrofit.create(API::class.java)
//
//    @Provides
//    @Singleton
//    fun provideDatabase(app: Application) : VehicleDatabase =
//        Room.databaseBuilder(app, VehicleDatabase::class.java, "vehicle_database")
//            .build()
}