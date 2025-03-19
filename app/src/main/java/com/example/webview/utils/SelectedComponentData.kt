package com.example.webview.utils

import android.app.Activity
import com.example.webview.R
import com.example.webview.constants.Constants

class SelectedComponentData(var activity: Activity) {
    fun getSelectedFontFamily(selectedValue: String?): String {
        return when (selectedValue) {
            Constants.FontFamily.roboto -> Constants.FontURLs.roboto
            Constants.FontFamily.raleWayItalic -> Constants.FontURLs.raleWayItalic
            Constants.FontFamily.delius -> Constants.FontURLs.delius
            Constants.FontFamily.ebGaramond -> Constants.FontURLs.ebGaramond
            Constants.FontFamily.dancingScript -> Constants.FontURLs.dancingScript
            else -> Constants.FontURLs.roboto
        }
    }

    fun getSelectedBackgroundColor(selectedValue: String?): Int {
        return when (selectedValue) {
            "Red" -> activity.getColor(R.color.red)
            "Blue" -> activity.getColor(R.color.teal_200)
            "Purple" -> activity.getColor(R.color.purple_500)
            "Pink" -> activity.getColor(R.color.pink_200)
            else -> activity.getColor(R.color.primary_color)
        }
    }
    fun getSelectedFontSize(selectedValue: String?): ArrayList<String> {
         return when (selectedValue){
             Constants.FontSize.bigSize -> arrayListOf("96px", "80px", "64px", "48px", "32px", "16px", "48px")
             Constants.FontSize.mediumSize -> arrayListOf("64px", "56px", "48px", "32px", "16px", "16px", "32px")
             Constants.FontSize.normalSize -> arrayListOf("48px", "40px", "32px", "16px", "16px", "16px", "16px")
             Constants.FontSize.smallSize -> arrayListOf("32px", "28.8px", "24px", "16px", "16px", "12px", "16px")
             else -> {arrayListOf("48px", "40px", "32px", "16px", "16px", "16px", "16px")}
         }
    }
}