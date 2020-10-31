package com.example.profiles.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/candidates--questionnaire.appspot.com/o/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProfileApiService {

    @GET("users.json?alt=media&token=e3672c23-b1a5-4ca7-bb77-b6580d75810c")
    suspend fun getProfiles(): MutableList<Profile>
}

object ProfileApi {
    val retrofitService : ProfileApiService by lazy { retrofit.create(ProfileApiService::class.java) }
}