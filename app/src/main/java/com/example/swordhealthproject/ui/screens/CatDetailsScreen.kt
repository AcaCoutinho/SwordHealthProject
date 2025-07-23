package com.example.swordhealthproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel
import java.nio.file.WatchEvent

@Composable
fun CatDetailsScreen (navController: NavHostController?, viewModel: CatBreedViewModel){
    var isFavourite by remember { mutableStateOf(viewModel.selectedCatBreed?.isFavourite) }

    Column (
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = viewModel.selectedCatBreed?.name?: "NOT FOUND",
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(3f)
            )
            IconButton(
                onClick = {
                    if(viewModel.selectedCatBreed?.isFavourite!!){
                        isFavourite = false
                        viewModel.deleteCatBreed(viewModel.selectedCatBreed!!)
                        viewModel.selectedCatBreed?.isFavourite = false
                    } else {
                        isFavourite = true
                        viewModel.selectedCatBreed?.isFavourite = true
                        viewModel.addToFavourites(viewModel.selectedCatBreed!!)
                    }
                },
                modifier = Modifier.weight(0.5f)
            ) {
                Icon(
                    imageVector = if (isFavourite!!) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavourite!!) "Selected icon button" else "Unselected icon button."
                )
            }
        }

        if(viewModel.selectedCatBreed?.image?.isEmpty() == false){
            AsyncImage(
                model = viewModel.selectedCatBreed?.image?: "NOT FOUND",
                contentDescription = "Cat Breed Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = "Lifespan: ${viewModel.selectedCatBreed?.life_span?: "NOT FOUND"}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 4.dp)
        )

        Text(
            text = "Description: ${viewModel.selectedCatBreed?.description?: "NOT FOUND"}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 4.dp)
        )
        Text(
            text = "Temperament: ${viewModel.selectedCatBreed?.temperament?: "NOT FOUND"}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 4.dp)
        )
    }
}

@Composable
fun LandscapeCatDetailsScreen (navController: NavHostController?, viewModel: CatBreedViewModel){
    var isFavourite by remember { mutableStateOf(viewModel.selectedCatBreed?.isFavourite) }

    Row (
        modifier = Modifier.fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight().weight(2f).padding(16.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = viewModel.selectedCatBreed?.name?: "NOT FOUND",
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(3f)
                )
                IconButton(
                    onClick = {
                        if(viewModel.selectedCatBreed?.isFavourite!!){
                            isFavourite = false
                            viewModel.deleteCatBreed(viewModel.selectedCatBreed!!)
                            viewModel.selectedCatBreed?.isFavourite = false
                        } else {
                            isFavourite = true
                            viewModel.selectedCatBreed?.isFavourite = true
                            viewModel.addToFavourites(viewModel.selectedCatBreed!!)
                        }
                    },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Icon(
                        imageVector = if (isFavourite!!) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite!!) "Selected icon button" else "Unselected icon button."
                    )
                }
            }

            if(viewModel.selectedCatBreed?.image?.isEmpty() == false){
                AsyncImage(
                    model = viewModel.selectedCatBreed?.image?: "NOT FOUND",
                    contentDescription = "Cat Breed Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                )
            }
        }

        Column (
            modifier = Modifier.fillMaxHeight().weight(2f).padding(16.dp)
        ) {
            Text(
                text = "Lifespan: ${viewModel.selectedCatBreed?.life_span?: "NOT FOUND"}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(0.dp, 4.dp)
            )

            Text(
                text = "Description: ${viewModel.selectedCatBreed?.description?: "NOT FOUND"}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(0.dp, 4.dp)
            )
            Text(
                text = "Temperament: ${viewModel.selectedCatBreed?.temperament?: "NOT FOUND"}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(0.dp, 4.dp)
            )
        }
    }
}