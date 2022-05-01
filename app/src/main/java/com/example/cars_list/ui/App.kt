package com.example.cars_list.ui

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this

        hashCode()
    }

    companion object {
        // Method to get app context. Will be used when need to interact with UI from a non-ui class.
        lateinit var appInstance: App
        fun getAppContext(): Context = appInstance.applicationContext
    }
}