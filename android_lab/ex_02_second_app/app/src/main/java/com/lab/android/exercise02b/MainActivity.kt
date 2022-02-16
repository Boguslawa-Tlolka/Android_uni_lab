package com.lab.android.exercise02b

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstAppButton = findViewById<Button>(R.id.firstAppButton)
        firstAppButton.setOnClickListener {
            val myAction = "com.example.exercise02.intent.action.OPEN_FIRST_APP"
            val myIntent = Intent(myAction)
            startActivity(myIntent)
        }

        val dialButton = findViewById<Button>(R.id.dialButton)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)

        dialButton.setOnClickListener {
            val phoneNumber: String = phoneEditText.text.toString()
            runDial(phoneNumber)
        }

        val geoLocButton = findViewById<Button>(R.id.geoLocButton)
        val geoLocEditText = findViewById<EditText>(R.id.geoLocEditText)

        geoLocButton.setOnClickListener {
            val geoLoc: String = geoLocEditText.text.toString()
            runGeoLoc(geoLoc)
        }
    }

    private fun runDial(phoneNum: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNum")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun runGeoLoc(location: String){
        val gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(location))
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}