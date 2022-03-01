package com.lab.android.media

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val audioButton: Button = findViewById(R.id.recordAudioButton)
        val playButton: Button = findViewById(R.id.playVideoButton)
        val recordVideoButton: Button = findViewById(R.id.recordVideoButton)

        audioButton.setOnClickListener {
            val intent = Intent(this, RecordAudioActivity::class.java)
            startActivity(intent)
        }

        playButton.setOnClickListener {
            val intent = Intent(this, PlayVideoActivity::class.java)
            startActivity(intent)
        }

        recordVideoButton.setOnClickListener {
            val intent = Intent(this, RecordVideoActivity::class.java)
            startActivity(intent)
        }
    }
}