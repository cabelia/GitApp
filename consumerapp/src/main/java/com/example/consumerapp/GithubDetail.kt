package com.example.consumerapp

data class GithubDetail (
    val id: Int,
    val avatarUrl: String,
    val company: String?,
    val followers: Int,
    val following: Int,
    val htmlUrl: String,
    val location: String,
    val login: String,
    val name: String,
    val publicRepos: Int
)