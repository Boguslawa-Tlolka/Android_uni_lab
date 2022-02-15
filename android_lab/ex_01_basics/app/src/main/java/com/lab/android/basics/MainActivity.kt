package com.lab.android.basics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            val myIntent1 = Intent(this, Activity1::class.java)
            startActivity(myIntent1)
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            val myIntent2 = Intent(this, Activity2::class.java)
            startActivity(myIntent2)
        }

        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            val myIntent3 = Intent(this, Activity3::class.java)
            startActivity(myIntent3)
        }
    }
}