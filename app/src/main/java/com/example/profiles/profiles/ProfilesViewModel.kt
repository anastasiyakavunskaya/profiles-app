package com.example.profiles.profiles

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.*
import com.example.profiles.database.getDatabase
import com.example.profiles.repository.ProfilesRepository
import kotlinx.coroutines.launch


class ProfilesViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val profilesRepository = ProfilesRepository(database)
    val profiles = profilesRepository.profiles
    var activity: Boolean = false


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


    fun isNetworkActive(cm: ConnectivityManager): Boolean{
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        cm.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {
                    activity = true
                }

                override fun onLost(network: Network) {
                    activity = false
                }
            })
        return activity
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfilesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProfilesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}