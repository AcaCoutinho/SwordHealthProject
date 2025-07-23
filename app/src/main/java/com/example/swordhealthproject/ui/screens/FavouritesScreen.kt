package com.example.swordhealthproject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.ui.composables.CatBreedCard
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel
import com.example.swordhealthproject.utils.Resource

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
                    color = MaterialTheme.colorScheme.tertiary
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
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }
        if(favourites.isNotEmpty()){
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp, 8.dp)
            ) {
                items(favourites) {
                    CatBreedCard(it, navController, catBreedViewModel)
                }
            }
        } else {
            Text(
                text = "There are no favourites",
                color = Color.Red
            )
        }
    }
}

@Composable
fun LandscapeFavouritesScreen (navController: NavHostController?, catBreedViewModel: CatBreedViewModel) {
    val context = LocalContext.current
    val favourites by catBreedViewModel.favourites.observeAsState(emptyList())

    Row (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().weight(2f).padding(8.dp)
        ) {
            Button(
                onClick = {
                    navController?.navigate(Screens.CATBREEDS.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Cat Breeds List",
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Already in this screen", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Favourites List",
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Column (
            modifier = Modifier.fillMaxHeight().weight(3f).padding(8.dp)
        ) {
            if(favourites.isNotEmpty()){
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp, 8.dp)
                ) {
                    items(favourites) {
                        CatBreedCard(it, navController, catBreedViewModel)
                    }
                }
            } else {
                Text(
                    text = "There are no favourites",
                    color = Color.Red
                )
            }
        }
    }
}