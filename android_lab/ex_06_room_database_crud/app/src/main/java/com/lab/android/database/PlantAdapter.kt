package com.lab.android.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantViewHolder>() {

    private var plantList = emptyList<Plant>()
    private var age = "Age: "

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        return PlantViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val currentItem = plantList[position]
        holder.itemView.nameTextView.text = currentItem.name

        val ageText = age + currentItem.age.toString()
        holder.itemView.ageTextView.text = ageText

        if (currentItem.place == "Garden")
            holder.itemView.placeImage.setImageResource(R.drawable.ic_garden)
        else holder.itemView.placeImage.setImageResource(R.drawable.ic_house)

        currentItem.color?.let { holder.itemView.placeImage.setColorFilter(it) }

        holder.itemView.cardView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    fun setData(plantList: List<Plant>) {
        this.plantList = plantList
        notifyDataSetChanged()
    }
}