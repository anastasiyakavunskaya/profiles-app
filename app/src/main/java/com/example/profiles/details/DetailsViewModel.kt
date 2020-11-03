package com.example.profiles.details

import android.app.Application
import androidx.lifecycle.*
import com.example.profiles.database.getDatabase
import com.example.profiles.network.Friend
import com.example.profiles.network.Profile
import com.example.profiles.repository.ProfilesRepository
import kotlinx.coroutines.launch


class DetailsViewModel(application: Application, friendsIds: MutableList<Friend>): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val profilesRepository = ProfilesRepository(database)
    val friends  = profilesRepository.getFriends(friendsIds)


    class Factory(val app: Application,val  friends: MutableList<Friend>) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app, friends) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}