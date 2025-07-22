package com.example.swordhealthproject.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.data.repository.CatBreedRepository
import com.example.swordhealthproject.utils.Resource
import kotlinx.coroutines.launch

class CatBreedViewModel (app: Application, val catBreedRepository: CatBreedRepository): AndroidViewModel(app){
    val catBreeds = MutableLiveData<Resource<List<CatBreed>>>()
    val favourites = MutableLiveData<List<CatBreed>>()
    var selectedCatBreed by mutableStateOf<CatBreed?>(null)

    var currentPage by mutableIntStateOf(1)

    init {
        loadFavourites()
        getCatBreeds()
    }

    fun getCatBreeds() = viewModelScope.launch {
        catBreeds.postValue(Resource.Loading())
        try {
            val response = catBreedRepository.getCatBreeds(page = currentPage - 1)
            if (response.isSuccessful) {
                response.body()?.let { newBreeds ->
                    val updatedBreeds = newBreeds.map { breed ->
                        val image = try {
                            val imgResponse = catBreedRepository.getImageCatBreed(breed.reference_image_id)
                            if (imgResponse.isSuccessful) {
                                imgResponse.body()
                            } else null
                        } catch (e: Exception) {
                            null
                        }
                        breed.copy(image = image?.url)
                    }
                    val syncCatBreed = syncFavouritesToCatBreeds(updatedBreeds, favourites.value ?: emptyList())
                    catBreeds.postValue(Resource.Success(syncCatBreed))
                } ?: run {
                    catBreeds.postValue(Resource.Error(message = "No results Found"))
                }
            } else {
                catBreeds.postValue(Resource.Error(message = response.message()))
            }
        } catch (e: Exception) {
            catBreeds.postValue(Resource.Error(message = e.message ?: "Unknown error"))
        }
    }

    fun searchCatBreed(query: String) = viewModelScope.launch {
        catBreeds.postValue(Resource.Loading())
        try {
            val response = catBreedRepository.searchCatBreed(query)
            if (response.isSuccessful) {
                response.body()?.let { newBreeds ->
                    val updatedBreeds = newBreeds.map { breed ->
                        val image = try {
                            val imgResponse = catBreedRepository.getImageCatBreed(breed.reference_image_id)
                            if (imgResponse.isSuccessful) {
                                imgResponse.body()
                            } else null
                        } catch (e: Exception) {
                            null
                        }
                        breed.copy(image = image?.url)
                    }
                    val syncCatBreed = syncFavouritesToCatBreeds(updatedBreeds, favourites.value ?: emptyList())
                    catBreeds.postValue(Resource.Success(syncCatBreed))
                } ?: run {
                    catBreeds.postValue(Resource.Error(message = "No results Found"))
                }
            } else {
                catBreeds.postValue(Resource.Error(message = response.message()))
            }
        } catch (e: Exception) {
            catBreeds.postValue(Resource.Error(message = e.message ?: "Unknown error"))
        }
    }

    fun addToFavourites(catBreed: CatBreed) = viewModelScope.launch {
        catBreedRepository.upsert(catBreed)
        loadFavourites()
    }

    fun loadFavourites() {
        catBreedRepository.getFavourites().observeForever { favList ->
            favourites.postValue(favList)
        }
    }

    fun deleteCatBreed(catBreed: CatBreed) = viewModelScope.launch {
        catBreedRepository.deleteCatBreeds(catBreed)
    }

    fun syncFavouritesToCatBreeds(catBreeds: List<CatBreed>, favourites: List<CatBreed>): List<CatBreed> {
        val favoritosIds = favourites.map { it.id }.toSet()
        return catBreeds.map { breed ->
            if (favoritosIds.contains(breed.id)) {
                breed.copy(isFavourite = true)
            } else {
                breed
            }
        }
    }
}