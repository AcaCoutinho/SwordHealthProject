package com.example.swordhealthproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swordhealthproject.data.entities.CatBreed

@Database(
    entities = [CatBreed::class],
    version = 1
)
abstract class CatBreedDatabase: RoomDatabase(){

    abstract fun getCatBreedDAO(): CatBreedDAO

    companion object{
        @Volatile
        private var instance: CatBreedDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context): CatBreedDatabase {
            return  Room.databaseBuilder(
                context.applicationContext,
                CatBreedDatabase::class.java,
                "catbreed.db"
            ).build()
        }
    }
}