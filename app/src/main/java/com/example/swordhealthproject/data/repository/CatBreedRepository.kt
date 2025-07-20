package com.example.swordhealthproject.data.repository

import com.example.swordhealthproject.data.api.RetrofitInstance
import com.example.swordhealthproject.data.database.CatBreedDatabase
import com.example.swordhealthproject.data.entities.CatBreed
import retrofit2.Retrofit

class CatBreedRepository(val db: CatBreedDatabase) {

    suspend fun getCatBreeds(limit: Int = 10, page: Int = 0) {
        RetrofitInstance.api.getCatBreeds(limit, page)
    }

    suspend fun searchCatBreed(searchQuery: String) {
        RetrofitInstance.api.searchCatBreeds(searchQuery)
    }

    suspend fun upsert(catBreed: CatBreed) {
        db.getCatBreedDAO().upsert(catBreed)
    }

    fun getFavourites(){
        db.getCatBreedDAO().getAllCatBreeds()
    }

    suspend fun deleteCatBreeds(catBreed: CatBreed) {
        db.getCatBreedDAO().deleteCatBreed(catBreed);
    }

}