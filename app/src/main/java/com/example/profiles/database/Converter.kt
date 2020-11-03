package com.example.profiles.database

import androidx.room.TypeConverter
import com.example.profiles.network.Friend

class Converter {
    @ExperimentalStdlibApi
    @TypeConverter
    fun fromFriends(value: String): MutableList<Friend> {
       val arrays: MutableList<String> =
            value.split("\\s*,\\s*".toRegex()).toMutableList()
        arrays.removeLast()
        return arrays.map {
            Friend(
                id = it.toInt())
        }.toMutableList()
    }

    @TypeConverter
    fun toFriends(list: MutableList<Friend>): String {
        var value = ""
        for (lang in list) value += "${lang.id},"
        return value
    }
}