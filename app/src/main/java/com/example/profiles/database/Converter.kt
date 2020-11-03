package com.example.profiles.database

import androidx.room.TypeConverter
import com.example.profiles.network.Friend

class Converter {
    @TypeConverter
    fun fromFriends(value: String): MutableList<Friend> {
       /* val arrays: MutableList<String> =
            value.split("\\s*,\\s*".toRegex()).toMutableList()
        return arrays.map {
            Friend(
                id = it.toInt())
        }.toMutableList()*/
        //TODO converter from Friends
        return mutableListOf(Friend(id = 1), Friend(id = 2))
    }

    @TypeConverter
    fun toFriends(list: MutableList<Friend>): String {
        var value = ""
        for (lang in list) value += "${lang.id},"
        return value
    }
}