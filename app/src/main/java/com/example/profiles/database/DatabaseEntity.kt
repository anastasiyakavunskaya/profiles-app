package com.example.profiles.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.profiles.network.*
import java.util.*

@Entity
@TypeConverters(Converter::class)
data class DatabaseProfile(
    @PrimaryKey
    val id: Int,
    val isActive: Boolean,
    val age: Int,
    val eyeColor: String,
    val name: String,
    val company: String,
    val email: String,
    val phone: String,
    val address: String,
    val about: String,
    val registered: String,
    val latitude: Double,
    val longitude: Double,
    val friends: MutableList<Friend>,
    val favoriteFruit: String)

fun List<DatabaseProfile>.asDomainModel(): List<Profile> {
    return map {
        Profile (
            id = it.id,
            isActive = it.isActive,
            age = it.age,
            eyeColor = it.eyeColor,
            name = it.name,
            company = it.company,
            email = it.email,
            phone = it.phone,
            address = it.address,
            about = it.about,
            registered = it.registered,
            latitude = it.latitude,
            longitude = it.longitude,
            friends = it.friends,
            favoriteFruit = it.favoriteFruit)
    }
}