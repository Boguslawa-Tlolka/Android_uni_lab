package com.lab.android.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)

        val toggle: ToggleButton = findViewById(R.id.textToggleButton)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                findViewById<TextView>(R.id.helloTextView).visibility = View.VISIBLE
            } else {
                // The toggle is disabled
                findViewById<TextView>(R.id.helloTextView).visibility = View.INVISIBLE
            }
        }

        var textRadio = ""
        var textToggle = ""

        val toastButton: Button = findViewById(R.id.toastButton)
        toastButton.setOnClickListener {
            textRadio = when (findViewById<RadioButton>(R.id.flowerRadioButton).isChecked) {
                true -> getString(R.string.flower_radio)
                else -> if (findViewById<RadioButton>(R.id.personRadioButton).isChecked) getString(R.string.person_radio)
                else getString(R.string.no_picture)
            }

            textToggle = when (toggle.isChecked) {
                true -> getString(R.string.visible)
                else -> getString(R.string.invisible)
            }

            val toast = Toast.makeText(this, "$textRadio\n$textToggle", Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun finishActivity1(view: View?){
        finish()
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.personRadioButton ->
                    if (checked) {
                        findViewById<ImageView>(R.id.contentImageView).setImageResource(R.drawable.ic_person)
                    }
                R.id.flowerRadioButton ->
                    if (checked) {
                        findViewById<ImageView>(R.id.contentImageView).setImageResource(R.drawable.ic_flower)
                    }
            }
        }
    }
}