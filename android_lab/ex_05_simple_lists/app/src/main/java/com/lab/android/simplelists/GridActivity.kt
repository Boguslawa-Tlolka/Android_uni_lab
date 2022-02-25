package com.lab.android.simplelists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val gridView: GridView = findViewById(R.id.gridView)
        val gridAdapter = GridAdapter(this)
        gridView.adapter = gridAdapter
    }
}