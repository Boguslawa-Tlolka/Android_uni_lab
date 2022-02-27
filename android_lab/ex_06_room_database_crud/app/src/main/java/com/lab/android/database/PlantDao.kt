package com.lab.android.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlant(plant: Plant)

    @Update
    suspend fun updatePlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)

    @Query("DELETE FROM plant_table")
    suspend fun deleteAllPlants()

    @Query("SELECT * FROM plant_table ORDER BY id ASC")
    fun readAllPlants(): LiveData<List<Plant>>
}