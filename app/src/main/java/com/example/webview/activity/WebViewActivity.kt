package com.example.webview.activity

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("WebLink")

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
                val googleFontsUrl =
                    "https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap"

                val code = """javascript:(function() { 
                    
                        var link = document.createElement('link');
                        link.rel = 'stylesheet';
                        link.href = '$googleFontsUrl';
                        document.head.appendChild(link);
    
                        var node = document.createElement('style');
                
                        node.type = 'text/css';
                        node.innerHTML = '
                        body{
                              background-color: #22CEEB;
                        }
                        h2{
                             color : #03DAC5 !important;
                             background-color: #FFFFFF;
                             font-family: "Dancing Script", serif !important;
                        }
                        body,h1{
                            color : #03DAC5;
                            font-family: "Dancing Script", serif !important;
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