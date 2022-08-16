package com.example.submission2.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.submission2.api.GithubDetail

@Dao
interface DatabaseFavDao {
    @Insert
    fun addToFavorite(githubDetail: GithubDetail)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<GithubDetail>>

    @Query("SELECT * FROM favorite_user ORDER BY id")
    fun checkUser(): Cursor

    @Delete
    fun removeFavorite(githubDetail: GithubDetail)

    @Query("SELECT * FROM favorite_user WHERE login = :username")
    fun findAll(username: String): LiveData<List<GithubDetail>>

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUsers(): List<GithubDetail>

    @Query("SELECT * FROM favorite_user")
    fun getFavorite(): Cursor

}