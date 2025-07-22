package com.example.swordhealthproject.utils

import androidx.room.TypeConverter
import com.example.swordhealthproject.data.entities.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromImage(image: Image?): String? {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(imageString: String?): Image? {
        return imageString?.let {
            Gson().fromJson(it, object : TypeToken<Image>() {}.type)
        }
    }
}