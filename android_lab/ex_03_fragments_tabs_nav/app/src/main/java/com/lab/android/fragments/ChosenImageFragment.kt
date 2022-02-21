package com.lab.android.fragments

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.edit

class ChosenImageFragment : Fragment() {

    private lateinit var galleryImages: TypedArray
    private lateinit var imageIDs: IntArray
    private var result: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chosen_image, container, false)
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

        parentFragmentManager.setFragmentResultListener("requestKeyPicture", viewLifecycleOwner){
                _, bundle ->
            result = bundle.getInt("bundleKeyPicture")
            val welcomeImageView = view.findViewById<ImageView>(R.id.chosenImageImageView)
            welcomeImageView.setImageResource(imageIDs[result])
        }

        val preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            preferences.edit {
                putInt("welcome_image", result)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChosenImageFragment()
    }
}