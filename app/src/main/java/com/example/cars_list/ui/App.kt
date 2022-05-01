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
        lateinit var appInstance: App
        fun getAppContext(): Context = appInstance.applicationContext
    }
}