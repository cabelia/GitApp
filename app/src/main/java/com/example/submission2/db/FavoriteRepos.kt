package com.example.submission2.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.submission2.api.GithubDetail


class FavoriteRepos(context: Context) {
    val favDao = Room.databaseBuilder(context, UserDatabase::class.java, UserDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration().build()

    val dao = favDao.favoriteUserDao()

    fun addToFavorite(githubDetail: GithubDetail) = dao.addToFavorite(githubDetail)
    fun getFavoriteUser() = dao.getFavoriteUser()
    fun findAll(username: String): LiveData<List<GithubDetail>> = dao.findAll(username)
    fun removeFavorite(githubDetail: GithubDetail) = dao.removeFavorite(githubDetail)
    fun getFavoriteUsers() = dao.getFavoriteUsers()
}
