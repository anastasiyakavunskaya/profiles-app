package com.example.profiles.profiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.profiles.network.Profile
import androidx.lifecycle.viewModelScope
import com.example.profiles.network.ProfileApi
import kotlinx.coroutines.launch


class ProfilesViewModel: ViewModel() {
    private var _profiles = MutableLiveData<List<Profile>>()
    val profiles: LiveData<List<Profile>> = _profiles

    fun getProfiles() {
        viewModelScope.launch {
            try {
                val result = ProfileApi.retrofitService.getProfiles()
                _profiles.value = result
            } catch (e: Exception) {
                _profiles.value = ArrayList()
            }
        }
    }
}