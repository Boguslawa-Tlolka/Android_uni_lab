package com.lab.android.fragments

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GalleryViewPagerAdapter(fg: Fragment): FragmentStateAdapter(fg) {

    private val IMAGES_COUNT = 3

    override fun getItemCount(): Int {
        return IMAGES_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return GalleryFragment.newInstance(position)
    }

}