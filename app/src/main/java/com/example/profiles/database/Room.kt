package com.example.profiles.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProfileDao {

    @Query("SELECT * FROM databaseProfile")
    fun getProfiles(): LiveData<List<DatabaseProfile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg profiles: DatabaseProfile)

    @Query("SELECT * FROM databaseProfile WHERE id IN(:profileId)")
    fun getFriend(profileId: ArrayList<Int>): LiveData<List<DatabaseProfile>>

}


@Database(entities = [DatabaseProfile::class], version = 1)
abstract class ProfilesDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
}

private lateinit var INSTANCE: ProfilesDatabase

fun getDatabase(context: Context): ProfilesDatabase {
    synchronized(ProfilesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ProfilesDatabase::class.java,
                "profiles").build()
        }
    }
    return INSTANCE
}