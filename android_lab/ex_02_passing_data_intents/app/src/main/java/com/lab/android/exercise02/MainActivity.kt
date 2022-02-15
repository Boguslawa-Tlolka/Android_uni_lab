package com.lab.android.exercise02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView = findViewById<View>(R.id.bottomNavigation) as BottomNavigationView
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.leftButton -> startActivity(Intent(this, ButtonsActivity::class.java))
                R.id.centerButton -> startActivity(Intent(this, DataActivity::class.java))
                R.id.rightButton -> startActivity(Intent(this, RelativeActivity::class.java))
            }
            true
        }
    }
}