package com.example.swordhealthproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.swordhealthproject.data.database.CatBreedDatabase
import com.example.swordhealthproject.data.repository.CatBreedRepository
import com.example.swordhealthproject.ui.screens.MainScreen
import com.example.swordhealthproject.ui.theme.SwordHealthProjectTheme
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModelProviderFactory

class MainActivity : ComponentActivity() {

    lateinit var catBreedViewModel: CatBreedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SwordHealthProjectTheme {
                MainScreen()
            }
        }

        val catBreedRepository = CatBreedRepository(CatBreedDatabase(this))
        val viewModelProviderFactory = CatBreedViewModelProviderFactory(application, catBreedRepository)
        catBreedViewModel = ViewModelProvider(this, viewModelProviderFactory).get(CatBreedViewModel::class)
    }

    override fun onResume() {
        super.onResume()
    }
}