package com.example.swordhealthproject.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class Image(
    var id: String,
    val url: String,
)