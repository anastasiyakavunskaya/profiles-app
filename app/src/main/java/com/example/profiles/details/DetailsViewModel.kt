package com.example.profiles.details

import android.app.Application
import android.location.Location.*
import androidx.lifecycle.*
import com.example.profiles.R
import com.example.profiles.database.getDatabase
import com.example.profiles.network.Profile
import com.example.profiles.repository.ProfilesRepository

import java.text.SimpleDateFormat
import java.util.*


class DetailsViewModel(application: Application, val profile: Profile): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val profilesRepository = ProfilesRepository(database)
    val friends  = profilesRepository.getFriends(profile.friends)

    fun formatDate(): String{
        val date = profile.registered

        var sdf = SimpleDateFormat("yyyy-mm-dd", Locale.US)
        var stf = SimpleDateFormat("hh-mm", Locale.US)
        val newDay: Date = sdf.parse(date)!!
        val newTime: Date = stf.parse(date)!!
        sdf = SimpleDateFormat("dd.mm.yy", Locale.US)
        stf = SimpleDateFormat("hh:mm", Locale.US)
        val result = stf.format(newTime) +" "+ sdf.format(newDay)
        return "Registered: $result"
    }

    fun formatLocation(): String {
        val lat = profile.latitude
        val lon = profile.longitude
        return convert(lat, FORMAT_DEGREES)+", "+ convert(lon, FORMAT_DEGREES)
    }
    fun getEyeColor(): Int{
        when(profile.eyeColor){
            "brown" -> return R.drawable.brown_eyes
            "green" -> return R.drawable.green_eyes
            "blue" -> return R.drawable.blue_eyes
        }
        return R.drawable.unknown
    }

    fun getFruit(): Int{
        when(profile.favoriteFruit){
        "banana" -> return R.drawable.bananas
        "apple" -> return R.drawable.apple
        "strawberry" -> return R.drawable.strawberry
        }
        return R.drawable.unknown
    }


    class Factory(val app: Application,val  profile: Profile) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app, profile) as T
            }
            throw IllegalArgumentException("Unable to construct viewModel")
        }
    }
}