package com.example.profiles.details

import android.app.Application
import android.content.Intent
import android.location.Location
import android.location.Location.*
import android.net.Uri
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.example.profiles.database.getDatabase
import com.example.profiles.network.Friend
import com.example.profiles.network.Profile
import com.example.profiles.repository.ProfilesRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class DetailsViewModel(application: Application, val profile: Profile): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val profilesRepository = ProfilesRepository(database)
    val friends  = profilesRepository.getFriends(profile.friends)

    fun formatDate(): String{
        val date = profile.registered

        var sdf = SimpleDateFormat("yyyy-mm-dd")
        var stf = SimpleDateFormat("hh-mm")
        val newDay: Date = sdf.parse(date)!!
        val newTime: Date = stf.parse(date)!!
        sdf = SimpleDateFormat("dd.mm.yy")
        stf = SimpleDateFormat("hh:mm")
        val result = stf.format(newTime) +" "+ sdf.format(newDay)
        return "Registered: $result"
    }

    fun formatCoordinates(): String {
        val lat = profile.latitude
        val lon = profile.longitude
        return "Location: "+ convert(lat, FORMAT_DEGREES)+", "+ convert(lon, FORMAT_DEGREES)
    }


    class Factory(val app: Application,val  profile: Profile) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app, profile) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}