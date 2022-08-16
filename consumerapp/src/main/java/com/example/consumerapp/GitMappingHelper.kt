package com.example.consumerapp

import android.database.Cursor

object GitMappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?):List<GithubDetail> {
        val list = arrayListOf<GithubDetail>()
        cursor?.apply {
            while (moveToNext()){
                val id = getInt(getColumnIndexOrThrow("id"))
                val name = getString(getColumnIndexOrThrow("name"))
                val company = getString(getColumnIndexOrThrow("company"))
                val location = getString(getColumnIndexOrThrow("location"))
                val publicRepos: Int = getInt(getColumnIndexOrThrow("public_repos"))
                val following = getInt(getColumnIndexOrThrow("following"))
                val followers = getInt(getColumnIndexOrThrow("followers"))
                val avatarUrl = getString(getColumnIndexOrThrow("avatar_url"))
                val login = getString(getColumnIndexOrThrow("login"))
                val htmlUrl = getString(getColumnIndexOrThrow("html_url"))
                list.add(GithubDetail(id,avatarUrl,company,followers,following,htmlUrl,location,login,name,publicRepos))
            }
        }
        return list
    }
}