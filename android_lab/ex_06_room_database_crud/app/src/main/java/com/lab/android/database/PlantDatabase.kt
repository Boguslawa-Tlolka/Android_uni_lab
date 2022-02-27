package com.lab.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase: RoomDatabase() {

    abstract fun plantDao(): PlantDao

    companion object{
        @Volatile
        private var DB_INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase{
            val tempInstance = DB_INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "plant_database"
                ).build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }
}