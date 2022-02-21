package com.lab.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ImagesFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var chosenImageFragment: ChosenImageFragment? = null
    lateinit var transaction: FragmentTransaction
    private val TAG_CHOSEN_FRAGMENT = "Chosen_fragment"

    private lateinit var viewPagerAdapter: GalleryViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        if (savedInstanceState == null) {
            chosenImageFragment = ChosenImageFragment.newInstance("param1", "param2")
            transaction = childFragmentManager.beginTransaction()
            transaction.add(R.id.imagesFragmentContainer, chosenImageFragment!!, this.TAG_CHOSEN_FRAGMENT)
            transaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPagerGallery)
        viewPagerAdapter = GalleryViewPagerAdapter(this)
        //viewPagerAdapter = GalleryViewPagerAdapter(parentFragmentManager)
        viewPager.adapter = viewPagerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImagesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}