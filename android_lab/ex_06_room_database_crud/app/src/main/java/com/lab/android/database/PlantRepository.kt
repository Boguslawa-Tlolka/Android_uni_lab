package com.lab.android.database

import androidx.lifecycle.LiveData

class PlantRepository (private val plantDao: PlantDao) {

    val readAllPlants: LiveData<List<Plant>> = plantDao.readAllPlants()

    suspend fun addPlant(plant: Plant){
        plantDao.addPlant(plant)
    }

    suspend fun updatePlant(plant: Plant){
        plantDao.updatePlant(plant)
    }

    suspend fun deletePlant(plant: Plant){
        plantDao.deletePlant(plant)
    }

    suspend fun deleteAllPlants(){
        plantDao.deleteAllPlants()
    }

}