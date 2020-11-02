package com.example.profiles.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.profiles.database.ProfilesDatabase
import com.example.profiles.database.asDomainModelProfile
import com.example.profiles.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfilesRepository(private val database: ProfilesDatabase) {

    val profiles: LiveData<List<Profile>> = Transformations
        .map(database.profileDao.getProfiles()) {
            it.asDomainModelProfile()
        }

    suspend fun refreshProfiles() {
        withContext(Dispatchers.IO) {
            try {
                val list =
                    NetworkProfileContainer(ProfileApi.retrofitService.getProfilesAsync())
                database.profileDao.insertAll(*list.asDatabaseModel())
            } catch (e: Exception){
                     }
        }

    }

}