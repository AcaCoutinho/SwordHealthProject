package com.example.swordhealthproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "catbreed")
data class CatBreed (
    @PrimaryKey
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val life_span: String,
    val description: String,
    val reference_image_id: String?,
    val image: String? = "",
    var isFavourite: Boolean = false
): Serializable