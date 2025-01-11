package com.gromo.masterdetailshowcase.core.persistence.api_impl.converters

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun stringToList(value: String?): List<String> = value?.let {
        value.split(",")
    } ?: emptyList()

    @TypeConverter
    fun listToString(list: List<String>): String = list.joinToString(",")
}
