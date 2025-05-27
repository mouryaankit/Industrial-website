package com.example.webview.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.R
import com.example.webview.constants.Constants
import com.example.webview.database.DataBase
import com.example.webview.databinding.ActivitySplashScreenBinding
import com.example.webview.utils.SharedPreferences

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var dataBase: DataBase
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webLogo.setImageResource(R.drawable.website_logo)
        dataBase = DataBase(this)
        bundle = Bundle()


        Handler(Looper.getMainLooper()).postDelayed({
            if (!SharedPreferences.getBoolean(Constants.SharedPreferences.IsFirstLaunch, false)) {
                val intent = Intent(this@SplashScreenActivity, WelcomeScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashScreenActivity, WebViewActivity::class.java)
                val configStyleData = dataBase.fetchData()
                if (configStyleData.size >= 1) {
                    bundle.putString(
                        Constants.ConfigComponents.fontFamily + "URL",
                        configStyleData[configStyleData.size - 1].fontUrl
                    )
                    bundle.putString(
                        Constants.ConfigComponents.fontFamily + "Name",
                        configStyleData[configStyleData.size - 1].fontName
                    )
                    bundle.putInt(
                        Constants.ConfigComponents.backgroundColor,
                        configStyleData[configStyleData.size - 1].backgroundColor
                    )
                    bundle.putStringArrayList(
                        Constants.ConfigComponents.fontSize,
                        configStyleData[configStyleData.size - 1].fontSize
                    )

                    val fontUrl = configStyleData[configStyleData.size - 1].fontUrl
                    Log.d(
                        "DATA--->",
                        configStyleData[configStyleData.size - 1].url + " " + configStyleData[configStyleData.size - 1].fontUrl + "" + configStyleData[configStyleData.size - 1].fontName + " " + configStyleData[configStyleData.size - 1].backgroundColor + " " + configStyleData[configStyleData.size - 1].fontSize + " "
                    )
                    intent.putExtra("WebStyles", bundle)
                    intent.putExtra("WebLink", configStyleData[configStyleData.size - 1].url)

                    startActivity(intent)
                    finish()
                }
            }
        }, 3000)

    }
}