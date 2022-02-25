package com.lab.android.simplelists

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView

class GridAdapter(c: Context): BaseAdapter(){
    private val context: Context = c

    private val images = listOf(
        R.drawable.ic_bus, R.drawable.ic_car, R.drawable.ic_truck,
        R.drawable.ic_car, R.drawable.ic_truck, R.drawable.ic_bus,
        R.drawable.ic_truck, R.drawable.ic_car, R.drawable.ic_bus
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(p0: Int): Any {
        return images[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(pos: Int, cV: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (cV == null) {
            imageView = ImageView(context)
            imageView.layoutParams = AbsListView.LayoutParams(200, 200)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else imageView = cV as ImageView
        imageView.setImageResource(images[pos])
        return imageView
    }
}