package com.example.swordhealthproject.data.api

import com.example.swordhealthproject.data.entities.CatBreed

data class CatBreedDto(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val life_span: String,
    val description: String,
    val image: ImageDto?
)

data class ImageDto(
    val url: String?
)

fun CatBreedDto.toCatBreed(): CatBreed {
    return CatBreed(
        id = id,
        breedName = name,
        temperament = temperament,
        origin = origin,
        lifespan = life_span,
        description = description,
        image = image?.url ?: ""
    )
}
