package com.lab.android.exercise02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

class RelativeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val progressText = findViewById<TextView>(R.id.progressTextView)

        seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                progressText.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                progressText.text = seekBar?.progress.toString()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                progressText.text = seekBar?.progress.toString()
            }

        })
    }

    fun finishActivity(view: View?){
        finish()
    }
}