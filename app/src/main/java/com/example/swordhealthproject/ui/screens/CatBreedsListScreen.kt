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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.swordhealthproject.R
import com.example.swordhealthproject.utils.Resource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedsListScreen (navController: NavHostController?, viewModel: CatBreedViewModel) {

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val catBreedsResource by viewModel.catBreeds.observeAsState()

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
            modifier = Modifier.padding(4.dp)
        ){
            Button(
                onClick = {
                    Toast.makeText(context, R.string.already_in_this_screen, Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(
                    text = stringResource(R.string.cat_breeds_list),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = {
                    navController?.navigate(Screens.FAVOURITES.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.favourites_list),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        ) {
            SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                    if(text.isEmpty()){
                        viewModel.getCatBreeds()
                    } else {
                        viewModel.searchCatBreed(text)
                    }
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(text = stringResource(R.string.search_for_the_cat_breed))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
                },
                trailingIcon = {
                    if(active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if(text.isNotEmpty()){
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_icon)
                        )
                    }
                }
            ) {

            }
        }

        when (catBreedsResource) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                val catList = (catBreedsResource as? Resource.Success<List<CatBreed>>)?.data?: emptyList()

                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp, 8.dp)
                ) {
                    items(catList) { catBreed ->
                        CatBreedCard(catBreed, navController, viewModel)
                    }
                    if(text.isEmpty()) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = {
                                        viewModel.currentPage--
                                        viewModel.getCatBreeds()
                                    },
                                    enabled = viewModel.currentPage > 1
                                ) {
                                    Icon(
                                        Icons.Default.ArrowBack,
                                        contentDescription = stringResource(R.string.previous_page)
                                    )
                                }

                                Text(
                                    text = "${viewModel.currentPage}",
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                )

                                IconButton(
                                    onClick = {
                                        viewModel.currentPage++
                                        viewModel.getCatBreeds()
                                    },
                                    enabled = catList.size == 10
                                ) {
                                    Icon(
                                        Icons.Default.ArrowForward,
                                        contentDescription = stringResource(R.string.next_page)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            is Resource.Error -> {
                val errorMsg = (catBreedsResource as Resource.Error).message
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: $errorMsg", color = Color.Red)
                }
            }

            null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(stringResource(R.string.not_found))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandscapeCatBreedsListScreen (navController: NavHostController?, viewModel: CatBreedViewModel) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val catBreedsResource by viewModel.catBreeds.observeAsState()

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
                    Toast.makeText(context, R.string.already_in_this_screen, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.cat_breeds_list),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Button(
                onClick = {
                    navController?.navigate(Screens.FAVOURITES.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.favourites_list),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    active = false
                    if(text.isEmpty()){
                        viewModel.getCatBreeds()
                    } else {
                        viewModel.searchCatBreed(text)
                    }
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(text = stringResource(R.string.search_for_the_cat_breed))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_for_the_cat_breed))
                },
                trailingIcon = {
                    if(active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if(text.isNotEmpty()){
                                    text = ""
                                    viewModel.getCatBreeds()
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.close_icon)
                        )
                    }
                }
            ) { }
        }

        Column (
            modifier = Modifier.fillMaxHeight().weight(3f).padding(8.dp)
        ) {
            when (catBreedsResource) {
                is Resource.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is Resource.Success -> {
                    val catList = (catBreedsResource as? Resource.Success<List<CatBreed>>)?.data?: emptyList()

                    LazyColumn (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp, 8.dp)
                    ) {
                        items(catList) { catBreed ->
                            CatBreedCard(catBreed, navController, viewModel)
                        }
                        if(text.isEmpty()) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    IconButton(
                                        onClick = {
                                            viewModel.currentPage--
                                            viewModel.getCatBreeds()
                                        },
                                        enabled = viewModel.currentPage > 1
                                    ) {
                                        Icon(
                                            Icons.Default.ArrowBack,
                                            contentDescription = stringResource(R.string.previous_page)
                                        )
                                    }

                                    Text(
                                        text = "${viewModel.currentPage}",
                                        modifier = Modifier.padding(horizontal = 16.dp),
                                    )

                                    IconButton(
                                        onClick = {
                                            viewModel.currentPage++
                                            viewModel.getCatBreeds()
                                        },
                                        enabled = catList.size == 10
                                    ) {
                                        Icon(
                                            Icons.Default.ArrowForward,
                                            contentDescription = stringResource(R.string.next_page)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                is Resource.Error -> {
                    val errorMsg = (catBreedsResource as Resource.Error).message
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error: $errorMsg", color = Color.Red)
                    }
                }

                null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(stringResource(R.string.not_found))
                    }
                }
            }
        }
    }
}