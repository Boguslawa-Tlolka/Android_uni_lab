package com.lab.android.exercise02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    val intent = result.data
                    val i = intent?.getDoubleExtra("result", 0.0)
                    if (i != null) {
                        val resultTextView = findViewById<TextView>(R.id.resultTextView)
                        resultTextView.text = String.format("%.1f", i)
                    }
                }
            }

        val bmiButton = findViewById<Button>(R.id.bmiButton)
        val weightEditText = findViewById<EditText>(R.id.weightEditText)
        val heightEditText = findViewById<EditText>(R.id.heightEditText)

        bmiButton.setOnClickListener {
            val weight: Double = weightEditText.text.toString().toDouble()
            val height: Double = heightEditText.text.toString().toDouble()

            val bundle = Bundle()
            bundle.putDouble("weight", weight)
            bundle.putDouble("height", height)

            val intent = Intent(this, BMIActivity::class.java)
            intent.putExtras(bundle)

            startForResult.launch(intent)
        }
    }
}