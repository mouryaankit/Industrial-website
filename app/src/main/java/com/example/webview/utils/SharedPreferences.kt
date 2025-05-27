package com.example.webview.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.webview.constants.Constants

object SharedPreferences {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(Constants.Intents.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue) ?: defaultValue
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue) ?: defaultValue
    }

    fun edit(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

}