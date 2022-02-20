package com.lab.android.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment{
        return when (position) {
            0 -> PersonTabFragment.newInstance("Tab1","Page # 1")
            1 -> ColorTabFragment.newInstance("Tab2", "Page # 2")
            else -> LikeTabFragment.newInstance("Tab3", "Page # 3")
        }
    }
}
