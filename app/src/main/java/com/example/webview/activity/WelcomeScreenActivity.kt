package com.example.webview.activity

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityWelcomeScreenBinding

class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (binding.etWeblink.text.isEmpty()) {
                Toast.makeText(this, "Please enter the link", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, WebViewActivity::class.java)
                intent.putExtra("WebLink", binding.etWeblink.text.toString())
                startActivity(intent)
            }
        }
        binding.scrollView.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            binding.scrollView.getWindowVisibleDisplayFrame(r)
            val screenHeight = binding.scrollView.rootView.height
            val keypadHeight = screenHeight - r.bottom

            if (keypadHeight > screenHeight * 0.15) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, binding.scrollView.bottom)
                }
            }
        }
    }
}