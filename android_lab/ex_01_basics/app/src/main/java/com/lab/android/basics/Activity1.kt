package com.lab.android.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
    }

    fun finishActivity1(view: View?){
        finish()
    }
}