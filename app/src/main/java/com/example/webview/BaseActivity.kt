package com.example.webview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.webview.databinding.ActivityBaseBinding
import com.google.android.material.navigation.NavigationView

open class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    private lateinit var drawerLayout: DrawerLayout
    lateinit var context: Context

    override fun setContentView(view: View) {
        drawerLayout = layoutInflater.inflate(R.layout.activity_base, null) as DrawerLayout
        val container = drawerLayout.findViewById<FrameLayout>(R.id.activity_container)
        container.addView(view)
        super.setContentView(drawerLayout)

        drawerLayout.findViewById<View>(R.id.top_app_bar).findViewById<View>(R.id.side_menu)
            .setOnClickListener { v: View? -> callNavigationDrawer() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun callNavigationDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.navigation_drawer)
        val toggle = ActionBarDrawerToggle(
            this, drawer, binding.topAppBar.openViewToolbar, R.string.nav_open, R.string.nav_close
        )

        drawer.openDrawer(GravityCompat.END)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val nav = findViewById<NavigationView>(R.id.nav_drawer_view)
        val v = nav.getHeaderView(0) as ConstraintLayout
        val btnNavBack = v.findViewById<ImageButton>(R.id.btn_back_from_nav)
        btnNavBack.setOnClickListener {
            drawer.closeDrawer(
                GravityCompat.END
            )
        }
    }
}