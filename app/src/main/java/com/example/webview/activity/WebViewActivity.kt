package com.example.webview.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.webview.R
import com.example.webview.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("WebLink")

        Toast.makeText(this,data,Toast.LENGTH_LONG).show()

        if (data != null) {
            binding.web.loadUrl(data)
        }

        binding.web.settings.javaScriptEnabled = true

        binding.web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                val code = """javascript:(function() { 
    
        var node = document.createElement('style');

        node.type = 'text/css';
        node.innerHTML = '@font-face {
            font-family: custom_font_bold_italic;
            src: url("file://assets/custom_font_bold_italic.ttf")
        }
        p,h1{
            background-color: #22CEEB !important;
        }
        body ,p,h1,h2{

            background-color: #f8f9fa;
            font-family: custom_font_bold_italic;
            font-size: 1rem;
            font-weight: 500;
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