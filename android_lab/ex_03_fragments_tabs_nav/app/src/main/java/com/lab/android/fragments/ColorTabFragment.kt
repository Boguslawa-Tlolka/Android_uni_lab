package com.lab.android.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.edit
import androidx.core.os.bundleOf

class ColorTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        val backgroundColor = preferences.getInt("background_color", 0)

        val colorSeekBar = view.findViewById<SeekBar>(R.id.colorSeekBar)
        val colorValueTextView = view.findViewById<TextView>(R.id.colorValueTextView)

        colorSeekBar.progress = backgroundColor
        colorValueTextView.setBackgroundColor(Color.rgb(backgroundColor, 255, 255))

        colorSeekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                colorValueTextView.setBackgroundColor(Color.rgb(progress, 255, 255))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    colorValueTextView.setBackgroundColor(Color.rgb(seekBar.progress, 255, 255))
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    colorValueTextView.setBackgroundColor(Color.rgb(seekBar.progress, 255, 255))
                }
            }
        })

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            preferences.edit {
                putInt("background_color", colorSeekBar.progress)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ColorTabFragment()
    }
}