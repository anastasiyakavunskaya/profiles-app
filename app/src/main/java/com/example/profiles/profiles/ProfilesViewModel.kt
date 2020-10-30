package com.example.profiles.profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profiles.network.Profile
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProfilesViewModel: ViewModel() {
    private var _profiles = MutableLiveData<List<Profile>>()
    val profiles: LiveData<List<Profile>> = _profiles

    fun getProfiles() {
        viewModelScope.launch {

        }
    }
}