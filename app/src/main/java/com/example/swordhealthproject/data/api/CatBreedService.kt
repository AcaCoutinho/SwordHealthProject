package com.example.swordhealthproject.data.api

import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.data.entities.Image
import com.example.swordhealthproject.utils.Constants.Companion.LIMIT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatBreedService {
    @GET("v1/breeds")
    suspend fun getCatBreeds(
        @Query("limit")
        limit: Int = LIMIT,
        @Query("page")
        page: Int = 0
    ): Response<List<CatBreed>>

    @GET("v1/breeds/search")
    suspend fun searchCatBreeds(
        @Query("q")
        q: String = "",
    ): Response<List<CatBreed>>

    @GET("v1/images/{id}")
    suspend fun getImageCatBreed (
        @Path("id")
        id: String?
    ): Response<Image>
}