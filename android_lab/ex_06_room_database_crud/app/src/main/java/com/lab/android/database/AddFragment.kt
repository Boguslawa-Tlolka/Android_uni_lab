package com.lab.android.database

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var plantViewModel: PlantViewModel
    private var red = 255
    private var green = 0
    private var blue = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        plantViewModel = ViewModelProvider(this).get(PlantViewModel::class.java)
        view.addButton.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var color: Int

        redSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                red = progress
                color = Color.rgb(red,green,blue)
                colorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    red = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    red = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

        })

        greenSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                green = progress
                color = Color.rgb(red,green,blue)
                colorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    green = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    green = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

        })

        blueSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                blue = progress
                color = Color.rgb(red,green,blue)
                colorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    blue = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    blue = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    colorTextView.setBackgroundColor(color)
                }
            }

        })
    }

    private fun insertDataToDatabase() {
        val plantName = nameOutlinedTextField.editText?.text.toString()
        val plantAge = ageOutlinedTextField.editText?.text.toString().toInt()

        val checkedHouse = houseRadioButton.isChecked
        val checkedGarden = gardenRadioButton.isChecked
        var place = ""
        if (checkedHouse) place = getString(R.string.house)
        if (checkedGarden) place = getString(R.string.garden)

        val plantColor = Color.rgb(red, green, blue)

        if (inputCheck(plantName, place)) {
            val newPlant = Plant(0, plantName, plantAge, place, plantColor)
            plantViewModel.addPlant(newPlant)
            Toast.makeText(requireContext(), "$plantName added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Fill out name and place!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(plantName: String, place: String): Boolean {
        return !(plantName.isEmpty() && place.isEmpty())
    }
}