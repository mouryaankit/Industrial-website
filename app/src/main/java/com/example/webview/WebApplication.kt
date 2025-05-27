package com.example.webview

import android.app.Application
import com.example.webview.utils.SharedPreferences

class WebApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferences.init(applicationContext)
    }
}