package com.example.swordhealthproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.ui.composables.CatBreedCard
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen (navController: NavHostController?, catBreedViewModel: CatBreedViewModel) {
    val context = LocalContext.current
    val favourites by catBreedViewModel.favourites.observeAsState(emptyList())

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        ){
            Button(
                onClick = {
                    navController?.navigate(Screens.CATBREEDS.route)
                }
            ) {
                Text(
                    text = "Cat Breeds List",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Already in this screen", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(
                    text = "Favourites List",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, 8.dp)
        ) {
            items(favourites) {
                CatBreedCard(it, navController, catBreedViewModel)
            }
        }
    }
}

@Composable
fun LandscapeFavouritesScreen (navController: NavHostController?,) {
    Text("FavouritesScreen")
}