package com.example.consumerapp

import android.content.UriMatcher
import android.net.Uri

object DatabaseContract {
    private const val MY_FAVORITE = 1
    private const val FAVORITE_ID = 2
    private const val AUTHORITY = "com.example.submission2"
    private const val SCHEME = "content"
    private const val TABLE_NAME = "favorite_user"
    private val matcher = UriMatcher(UriMatcher.NO_MATCH)
    val CONTENT_URI = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

    init {
        matcher.addURI(AUTHORITY, TABLE_NAME, MY_FAVORITE)
        matcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
    }
}