package com.lab.android.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavView = findViewById<View>(R.id.bottomNav) as BottomNavigationView
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item1 -> navController.navigate(R.id.action_global_mainFragment)
                R.id.item2 -> navController.navigate(R.id.action_global_settingsFragment)
                R.id.item3 -> navController.navigate(R.id.action_global_rateFragment)
                R.id.item4 -> navController.navigate(R.id.action_global_imagesFragment)
            }
            true
        }
    }
}