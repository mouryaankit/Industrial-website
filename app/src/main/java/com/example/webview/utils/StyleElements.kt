package com.example.webview.utils

import com.example.webview.constants.Constants

class StyleElements {

    fun getFontFamilyStyles() :ArrayList<String>{
        val fontFamilyList = ArrayList<String>()
        fontFamilyList.add(Constants.FontFamily.roboto)
        fontFamilyList.add(Constants.FontFamily.raleWayItalic)
        fontFamilyList.add(Constants.FontFamily.delius)
        fontFamilyList.add(Constants.FontFamily.ebGaramond)
        fontFamilyList.add(Constants.FontFamily.dancingScript)
        return fontFamilyList
    }
    fun getBackgroundColorStyle() :ArrayList<String>{
        val backgroundColorList = ArrayList<String>()
        backgroundColorList.add("Red")
        backgroundColorList.add("Blue")
        backgroundColorList.add("Purple")
        backgroundColorList.add("Pink")

        return backgroundColorList
    }

    fun getFontSizeStyle() : ArrayList<String>{
        val fontSizeList = ArrayList<String>()
        fontSizeList.add(Constants.FontSize.bigSize)
        fontSizeList.add(Constants.FontSize.mediumSize)
        fontSizeList.add(Constants.FontSize.normalSize)
        fontSizeList.add(Constants.FontSize.smallSize)
        return  fontSizeList
    }
}