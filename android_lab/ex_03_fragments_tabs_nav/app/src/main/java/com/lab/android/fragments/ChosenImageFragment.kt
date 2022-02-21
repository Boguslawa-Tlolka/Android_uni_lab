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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ChosenImageFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var galleryImages: TypedArray
    private lateinit var imageIDs: IntArray
    private var result: Int = 0

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
                requestKey, bundle ->
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
        fun newInstance(param1: String, param2: String) =
            ChosenImageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}