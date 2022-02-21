package com.lab.android.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textsButton: Button = view.findViewById(R.id.textsButton)
        val imagesButton: Button = view.findViewById(R.id.imagesButton)

        textsButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_nav_texts)
        }

        imagesButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_nav_home_to_nav_images)
        }

    }

}