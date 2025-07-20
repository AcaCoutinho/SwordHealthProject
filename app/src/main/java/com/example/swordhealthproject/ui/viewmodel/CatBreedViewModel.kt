package com.example.swordhealthproject.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.data.repository.CatBreedRepository
import com.example.swordhealthproject.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CatBreedViewModel (app: Application, val catBreedRepository: CatBreedRepository): AndroidViewModel(app){
    val catBreeds: MutableLiveData<Resource<CatBreed>> = MutableLiveData()
    var page = 1
    var catBreedsResponse: CatBreed?=null

    val catBreedSearch: MutableLiveData<Resource<CatBreed>> = MutableLiveData()
    var searchCatBreedResponse: CatBreed?= null
    var searchQuery: String?= null

    private fun handleCatBreedsResponse(response: Response<CatBreed>): Resource<CatBreed> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                page++
                return Resource.Success(catBreedsResponse ?: resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }

    private fun handleCatBreedSearchResponse(response: Response<CatBreed>): Resource<CatBreed> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(catBreedsResponse ?: resultResponse)
            }
        }
        return Resource.Error(message = response.message())
    }

    fun addFavourites(catBreed: CatBreed) {
        viewModelScope.launch {
            catBreedRepository.upsert(catBreed)
        }
    }

    fun getFavourites() {
        catBreedRepository.getFavourites()
    }

    fun internetConnection(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run{
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }

    //private suspend fun

}