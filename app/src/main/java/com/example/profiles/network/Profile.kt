package com.example.profiles.network

import java.io.Serializable

data class Profile(
val id: Int,
//val guid: String,
val isActive: Boolean,
//val balance: String,
val age: Int,
val eyeColor: String,
val name: String,
//val gender: String,
val company: String,
val email: String,
val phone: String,
val address: String,
val about: String,
val registered: String,
//val latitude: Double,
//val longitude: Double,
//val friends: ArrayList<Int>,
val favoriteFruit: String
): Serializable
