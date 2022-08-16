package com.example.submission2.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.example.submission2.db.DatabaseFavDao
import com.example.submission2.db.UserDatabase

class UserContentProvider: ContentProvider() {
    private lateinit var dao: DatabaseFavDao

    companion object {
        private const val MY_FAVORITE = 1
        private const val FAVORITE_ID = 2
        private const val AUTHORITY = "com.example.submission2"
        private const val SCHEME = "content"
        private const val TABLE_NAME = "favorite_user"
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()
        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, MY_FAVORITE)
            uriMatcher.addURI(AUTHORITY, "$TABLE_NAME/#", FAVORITE_ID)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun onCreate(): Boolean {
        val db = Room.databaseBuilder(context as Context, UserDatabase::class.java, UserDatabase.DATABASE_NAME).fallbackToDestructiveMigration().build()
        dao = db.favoriteUserDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            MY_FAVORITE -> dao.checkUser()
            else -> null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}