package com.lab.android.fragments

import android.media.Rating
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.core.content.edit

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RateFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        val rate = preferences.getFloat("rate", 0.0F)

        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        ratingBar.rating = rate

        ratingBar.setOnRatingBarChangeListener(object:
            RatingBar.OnRatingBarChangeListener {
            override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
                preferences.edit {
                    putFloat("rate", p1)
                }
            }
            }
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RateFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}