package com.example.profiles.network

import java.io.Serializable

data class Profile(
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
val favoriteFruit: String
): Serializable

data class Friend(
     val id:Int
):Serializable