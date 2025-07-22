package com.example.swordhealthproject.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.swordhealthproject.data.entities.CatBreed
import com.example.swordhealthproject.ui.screens.Screens
import com.example.swordhealthproject.ui.viewmodel.CatBreedViewModel

@Composable
fun CatBreedCard(catBreed: CatBreed, navController: NavHostController?, viewModel: CatBreedViewModel) {
    var isFavourite by remember { mutableStateOf(catBreed.isFavourite) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            viewModel.selectedCatBreed = catBreed
            navController?.navigate(Screens.CATDETAILS.route)
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = catBreed.image,
                contentDescription = "Cat Breed Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
            )

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = catBreed.name, fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(3f)
                )
                IconButton(
                    onClick = {
                        if(catBreed.isFavourite){
                            isFavourite = false
                            viewModel.deleteCatBreed(catBreed)
                            catBreed.isFavourite = false
                        } else {
                            isFavourite = true
                            catBreed.isFavourite = true
                            viewModel.addToFavourites(catBreed)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite) "Selected icon button" else "Unselected icon button."
                    )
                }
            }
        }
    }
}