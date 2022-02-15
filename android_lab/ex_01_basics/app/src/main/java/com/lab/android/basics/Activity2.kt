package com.lab.android.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val toastButton: Button = findViewById(R.id.toastButton)
        toastButton.setOnClickListener {
            Toast.makeText(this,getString(R.string.toast_act_2), Toast.LENGTH_SHORT).show()
        }
    }

    fun finishActivity2(view: View?){
        finish()
    }
}