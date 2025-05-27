package com.example.webview.model

data class WebsiteStyleConfigData(
    val url: String,
    var fontUrl: String,
    var fontName: String,
    var backgroundColor: Int,
    var fontSize: ArrayList<String>
)
