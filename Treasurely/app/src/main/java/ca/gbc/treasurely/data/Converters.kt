package ca.gbc.treasurely.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromList(list: List<String>): String =
        list.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> =
        if (data.isBlank()) emptyList() else data.split(",")
}