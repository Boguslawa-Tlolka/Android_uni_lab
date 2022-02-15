package com.lab.android.exercise02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        val intent: Intent = intent
        val bundle = intent.extras

        val weight: Double? = bundle?.getDouble("weight",0.0)
        val height: Double? = bundle?.getDouble("height",0.0)

        val weightTextView = findViewById<TextView>(R.id.weightValueTextView)
        weightTextView.text = weight.toString()
        val heightTextView = findViewById<TextView>(R.id.heightValueTextView)
        heightTextView.text = height.toString()

        val calcButton = findViewById<Button>(R.id.calcButton)
        calcButton.setOnClickListener {
            val bmi = height?.let {
                weight?.div(it.div(100.0).times(it.div(100.0)))
            }
            intent.putExtra("result", bmi)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}