package com.lab.android.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class ImagesFragment : Fragment() {

    private var chosenImageFragment: ChosenImageFragment? = null
    private lateinit var transaction: FragmentTransaction
    private val TAG_CHOSEN_FRAGMENT = "Chosen_fragment"

    private lateinit var viewPagerAdapter: GalleryViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            chosenImageFragment = ChosenImageFragment.newInstance()
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
        viewPager.adapter = viewPagerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImagesFragment()
    }
}