package com.example.swordhealthproject.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swordhealthproject.data.repository.CatBreedRepository

class CatBreedViewModelProviderFactory(val app: Application, val catBreedRepository: CatBreedRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatBreedViewModel(app, catBreedRepository) as T
    }
}