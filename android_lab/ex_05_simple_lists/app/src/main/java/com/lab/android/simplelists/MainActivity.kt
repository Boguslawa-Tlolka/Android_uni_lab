package com.lab.android.simplelists

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val simpleListButton: Button = findViewById(R.id.simpleListButton)
        simpleListButton.setOnClickListener {
            val intent = Intent(this, SimpleListActivity::class.java)
            startActivity(intent)
        }

        val multipleChoiceListButton: Button = findViewById(R.id.multipleChoiceListButton)
        multipleChoiceListButton.setOnClickListener {
            val intent = Intent(this, MultipleChoiceActivity::class.java)
            startActivity(intent)
        }

        val gridButton: Button = findViewById(R.id.gridButton)
        gridButton.setOnClickListener {
            val intent = Intent(this, GridActivity::class.java)
            startActivity(intent)
        }
    }
}