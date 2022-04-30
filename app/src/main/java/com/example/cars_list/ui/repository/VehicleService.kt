package com.example.cars_list.ui.repository

import com.example.cars_list.ui.data.entity.Listings
import com.example.cars_list.ui.data.entity.Property

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://carfax-for-consumers.firebaseio.com/"



private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

    interface VehicleService{

        @GET("assignment.json")
        fun getVehicles(): Call<Listings>

    }

    object Api {
        val retrofitService: VehicleService by lazy{retrofit.create(VehicleService::class.java)}
    }