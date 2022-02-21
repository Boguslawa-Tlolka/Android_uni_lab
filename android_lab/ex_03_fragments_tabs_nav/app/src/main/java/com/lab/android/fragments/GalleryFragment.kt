package com.lab.android.fragments

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf

private const val ARG_PARAM1 = "param1"

class GalleryFragment : Fragment() {

    private var param1: Int? = null

    private lateinit var galleryImages: TypedArray
    private lateinit var imageIDs: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
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
        val imageView: ImageView = view.findViewById(R.id.galleryImageView)
        if (param1 != null) {
            imageView.setImageResource(imageIDs[param1!!])
        }

        imageView.setOnClickListener {
            val result = param1
            parentFragmentManager.setFragmentResult("requestKeyPicture", bundleOf("bundleKeyPicture" to result))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}