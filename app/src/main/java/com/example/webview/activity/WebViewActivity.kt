package com.example.webview.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.R
import com.example.webview.constants.Constants
import com.example.webview.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding
    lateinit var bundle :Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("WebLink")
        bundle = intent.getBundleExtra("WebStyles")!!
        val fontFamilyURL = bundle.getString(Constants.ConfigComponents.fontFamily + "URL", null)
        val fontFamilyName = bundle.getString(Constants.ConfigComponents.fontFamily + "Name", null)
        val backgroundColor = bundle.getInt(Constants.ConfigComponents.backgroundColor, resources.getColor(R.color.primary_color))
        val fontSizeList = bundle.getStringArrayList(Constants.ConfigComponents.fontSize)


        if (data != null) {
            binding.web.loadUrl(data)
        }

        binding.web.settings.javaScriptEnabled = true

        binding.web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val hexValue = java.lang.String.format("#%06X", 0xFFFFFF and backgroundColor)

                val code = """javascript:(function() { 
                    
                        var link = document.createElement('link');
                        link.rel = 'stylesheet';
                        link.href = '$fontFamilyURL';
                        document.head.appendChild(link);
    
                        var node = document.createElement('style');
                
                        node.type = 'text/css';
                        node.innerHTML = '
                        body{
                              background-color: $hexValue;
                        }
                        h1{
                            font-size: ${fontSizeList?.get(0)};
                        }
                        h2{
                             background-color: #FFFFFF;
                             font-size : ${fontSizeList?.get(1)};
                        }
                        h3{
                            font-size : ${fontSizeList?.get(2)};
                        }
                        h4{
                            font-family: $fontFamilyName, serif !important;
                            font-size : ${fontSizeList?.get(3)};
                        }
                       p{
                        font-size: ${fontSizeList?.get(6)}
                       }
                        body,h1,h2{
                            color : #03DAC5;
                            font-family: $fontFamilyName, serif !important;
                        }';
                
                        document.head.appendChild(node);
                     
                    })()""".trimIndent()

                if (data != null) {
                    binding.web.loadUrl(code)
                }
            }
        }
    }
}