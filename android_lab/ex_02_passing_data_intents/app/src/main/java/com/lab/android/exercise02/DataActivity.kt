package com.lab.android.exercise02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

            var weight: Double? = null
            var height: Double? = null

            if (weightEditText.text.isNotEmpty() && heightEditText.text.isNotEmpty()) {
                weight = weightEditText.text.toString().toDouble()
                height = heightEditText.text.toString().toDouble()
            }
            else Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_SHORT).show()

            if (weight != null && height != null) {
                val bundle = Bundle()
                bundle.putDouble("weight", weight)
                bundle.putDouble("height", height)

                val intent = Intent(this, BMIActivity::class.java)
                intent.putExtras(bundle)

                startForResult.launch(intent)
            }
        }
    }
}