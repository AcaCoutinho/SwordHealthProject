package com.example.swordhealthproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "catbreed")
data class CatBreed (
    @PrimaryKey
    val id: String,
    val breedName: String,
    val temperament: String,
    val origin: String,
    val lifespan: String,
    val description: String,
    val image: String
): Serializable