package com.example.submission2.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_user")
data class GithubDetail(
    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @ColumnInfo(name = "company")
    @SerializedName("company")
    val company: String?,
    @ColumnInfo(name = "followers")
    @SerializedName("followers")
    val followers: Int,
    @ColumnInfo(name = "following")
    @SerializedName("following")
    val following: Int,
    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    val htmlUrl: String,
    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "location")
    @SerializedName("location")
    val location: String?,
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,
    @ColumnInfo(name = "public_repos")
    @SerializedName("public_repos")
    val publicRepos: Int
)