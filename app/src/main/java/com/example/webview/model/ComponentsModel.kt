package com.example.webview.model

data class ComponentsModel(
    val componentName: String,
    val componentList: List<String>,
    var expandable: Boolean = false,
    var selectedValue: String? = null
)
