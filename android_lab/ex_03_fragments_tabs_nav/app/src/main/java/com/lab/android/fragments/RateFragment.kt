package com.lab.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.core.content.edit

class RateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        val rating = preferences.getFloat("rate", 0.0F)

        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.rating = rating

        ratingBar.setOnRatingBarChangeListener { _, p1, _ ->
            preferences.edit {
                putFloat("rate", p1)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RateFragment()
    }
}