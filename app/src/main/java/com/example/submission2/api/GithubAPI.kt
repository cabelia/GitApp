package com.example.submission2.api

import retrofit2.http.*

interface GithubAPI {

    @GET("/search/users")
    @Headers("Authorization: token 72383e14717ee40f9d6b7b632f9ad075512bccdf")
    fun getGit(@Query("q") q: String): retrofit2.Call<GithubResp>

    @GET("/users/{username}")
    @Headers("Authorization: token 72383e14717ee40f9d6b7b632f9ad075512bccdf")
    fun getDetailGit(@Path("username") username: String): retrofit2.Call<GithubDetail>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token 72383e14717ee40f9d6b7b632f9ad075512bccdf")
    fun getFollowers(@Path("username") username: String): retrofit2.Call<List<Item>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token 72383e14717ee40f9d6b7b632f9ad075512bccdf")
    fun getFollowing(@Path("username") username: String): retrofit2.Call<List<Item>>
}