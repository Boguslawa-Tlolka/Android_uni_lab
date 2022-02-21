package com.lab.android.fragments

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class MainFragment : Fragment() {

    private lateinit var galleryImages: TypedArray
    private lateinit var imageIDs: IntArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        galleryImages = requireActivity().resources.obtainTypedArray(R.array.gallery_images)
        val length = galleryImages.length()
        imageIDs = IntArray(length)
        for (i in 0 until length){
            imageIDs[i] = galleryImages.getResourceId(i, 0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        view.setBackgroundColor(Color.rgb(preferences.getInt("background_color", 255), 255, 255))

        val imageView = view.findViewById<ImageView>(R.id.welcomeImageView)
        val value = preferences.getInt("welcome_image", 0)
        imageView.setImageResource(imageIDs[value])
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}