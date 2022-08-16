package com.example.submission2.api

import com.google.gson.annotations.SerializedName

data class GithubResp(
    @SerializedName("Incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)