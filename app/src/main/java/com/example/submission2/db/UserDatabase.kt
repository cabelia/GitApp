package com.example.submission2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission2.api.GithubDetail

@Database(
    entities = [GithubDetail::class],
    version = 1

)
abstract class UserDatabase : RoomDatabase() {
    abstract fun favoriteUserDao(): DatabaseFavDao

    companion object {

        @Volatile
        private var instance: UserDatabase? = null
        private var LOCK = Any()
        const val DATABASE_NAME = "db_favorite"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }


}
