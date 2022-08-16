package com.example.submission2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubService {
    val depRetrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubAPI::class.java)
}