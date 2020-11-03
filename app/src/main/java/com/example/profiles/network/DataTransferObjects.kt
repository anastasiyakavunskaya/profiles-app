package com.example.profiles.network

import android.net.Network
import androidx.room.Database
import androidx.room.TypeConverters
import com.example.profiles.database.Converter
import com.example.profiles.database.DatabaseProfile
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProfileContainer(val profiles: List<Profile>)


fun NetworkProfileContainer.asDatabaseModel(): Array<DatabaseProfile> {
    return profiles.map {
        DatabaseProfile (
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
    }.toTypedArray()
}


