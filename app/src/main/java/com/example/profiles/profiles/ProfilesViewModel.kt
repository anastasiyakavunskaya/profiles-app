package com.example.profiles.profiles

import android.app.Application
import androidx.lifecycle.*
import com.example.profiles.database.getDatabase
import com.example.profiles.network.Profile
import com.example.profiles.network.ProfileApi
import com.example.profiles.repository.ProfilesRepository
import kotlinx.coroutines.launch


class ProfilesViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val profilesRepository = ProfilesRepository(database)
    val profiles = profilesRepository.profiles

    fun refreshProfiles(): Boolean{
        return try {
            viewModelScope.launch {
                profilesRepository.refreshProfiles()
            }
            true
        } catch (e: Exception){
            false
        }
    }




    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfilesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfilesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}