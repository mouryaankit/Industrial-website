package com.example.webview.database

import android.content.Context
import com.example.webview.model.WebsiteStyleConfigData
import com.example.webview.utils.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataBase(val context: Context) {
    private var gson: Gson = Gson()


    fun fetchData(): ArrayList<WebsiteStyleConfigData> {
        val listType = object :
            TypeToken<ArrayList<WebsiteStyleConfigData?>?>() {}.type
        val fetchData: String? = SharedPreferences.getString("data", null)
        return gson.fromJson<ArrayList<WebsiteStyleConfigData>>(fetchData, listType)
            ?: return ArrayList()
    }

    fun saveData(list: ArrayList<WebsiteStyleConfigData>?) {

        val editor = SharedPreferences.edit()
        val listData = gson.toJson(list)
        SharedPreferences.putString("data", listData)
        editor.apply()
    }
}