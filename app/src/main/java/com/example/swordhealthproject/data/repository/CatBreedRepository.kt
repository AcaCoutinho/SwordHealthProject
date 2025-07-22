package com.example.swordhealthproject.data.repository

import androidx.lifecycle.LiveData
import com.example.swordhealthproject.data.api.RetrofitInstance
import com.example.swordhealthproject.data.database.CatBreedDatabase
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.data.entities.Image
import retrofit2.Response

class CatBreedRepository(val db: CatBreedDatabase) {

    suspend fun getCatBreeds(limit: Int = 10, page: Int = 0): Response<List<CatBreed>> {
        return RetrofitInstance.api.getCatBreeds(limit, page)
    }

    suspend fun searchCatBreed(searchQuery: String) : Response<List<CatBreed>>{
        return RetrofitInstance.api.searchCatBreeds(searchQuery)
    }

    suspend fun getImageCatBreed(id: String?): Response<Image> {
        return RetrofitInstance.api.getImageCatBreed(id)
    }

    suspend fun upsert(catBreed: CatBreed){
        db.getCatBreedDAO().upsert(catBreed)
    }

    fun getFavourites(): LiveData<List<CatBreed>> {
        return db.getCatBreedDAO().getAllFavourites()
    }

    suspend fun deleteCatBreeds(catBreed: CatBreed) {
        db.getCatBreedDAO().deleteCatBreed(catBreed);
    }

}