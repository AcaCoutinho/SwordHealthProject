package com.example.swordhealthproject.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.swordhealthproject.data.entities.CatBreed


@Dao
interface CatBreedDAO {
    @Upsert()
    suspend fun upsert(catBreed: CatBreed) : Long

    @Query("SELECT * FROM catbreed")
    fun getAllFavourites(): LiveData<List<CatBreed>>

    @Delete
    suspend fun deleteCatBreed(catBreed: CatBreed)
}