package com.lab.android.database

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var plantViewModel: PlantViewModel
    private var red = 0
    private var green = 0
    private var blue = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        plantViewModel = ViewModelProvider(this).get(PlantViewModel::class.java)

        view.updateNameOutlinedTextField.editText?.setText(args.currentPlant.name)
        view.updateAgeOutlinedTextField.editText?.setText(args.currentPlant.age.toString())

        if (args.currentPlant.place == getString(R.string.garden)){
            view.updateGardenRadioButton.isChecked = true
        } else view.updateHouseRadioButton.isChecked = true

        red = args.currentPlant.color?.red ?: 0
        green = args.currentPlant.color?.green ?: 0
        blue = args.currentPlant.color?.blue ?: 0
        args.currentPlant.color?.let { view.updateColorTextView.setBackgroundColor(it) }
        view.updateRedSeekBar.progress = red
        view.updateGreenSeekBar.progress = green
        view.updateBlueSeekBar.progress = blue

        view.updateButton.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val plantName = updateNameOutlinedTextField.editText?.text.toString()
        val plantAge = updateAgeOutlinedTextField.editText?.text.toString().toInt()

        val checkedHouse = updateHouseRadioButton.isChecked
        val checkedGarden = updateGardenRadioButton.isChecked
        var plantPlace = ""
        if (checkedHouse) plantPlace = getString(R.string.house)
        if (checkedGarden) plantPlace = getString(R.string.garden)

        val plantColor = Color.rgb(updateRedSeekBar.progress, updateGreenSeekBar.progress, updateBlueSeekBar.progress)

        if (inputCheck(plantName, plantPlace)){
            val updatedPlant = Plant(args.currentPlant.id, plantName, plantAge, plantPlace, plantColor)
            plantViewModel.updatePlant(updatedPlant)
            Toast.makeText(requireContext(), "$plantName updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out name and place!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(plantName: String, plantPlace: String): Boolean {
        return !(plantName.isEmpty() && plantPlace.isEmpty())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var color: Int

        updateRedSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                red = progress
                color = Color.rgb(red,green,blue)
                updateColorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    red = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    red = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

        })

        updateGreenSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                green = progress
                color = Color.rgb(red,green,blue)
                updateColorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    green = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    green = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

        })

        updateBlueSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                blue = progress
                color = Color.rgb(red,green,blue)
                updateColorTextView.setBackgroundColor(color)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    blue = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    blue = seekBar.progress
                    color = Color.rgb(red,green,blue)
                    updateColorTextView.setBackgroundColor(color)
                }
            }

        })
    }

}