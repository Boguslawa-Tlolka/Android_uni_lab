package com.lab.android.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int?,
    val place: String,
    val color: Int?
)