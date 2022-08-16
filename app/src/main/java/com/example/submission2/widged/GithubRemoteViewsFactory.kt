package com.example.submission2.widged

import android.content.Context
import android.content.Intent
import android.security.KeyChain.EXTRA_NAME
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.submission2.R
import com.example.submission2.api.GithubDetail
import com.example.submission2.db.FavoriteRepos

internal class GithubRemoteViewsFactory(private val context: Context) :
    RemoteViewsService.RemoteViewsFactory {
    private val userFav = mutableListOf<GithubDetail>()

    override fun onCreate() {

    }

    override fun onDataSetChanged() {
        val favUsers = FavoriteRepos(context).getFavoriteUsers()
        userFav.clear()
        userFav.addAll(favUsers)
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int = userFav.size

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.item_widged)

        if (userFav.isNotEmpty()) {
            try {
                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(userFav[position].avatarUrl)
                    .submit()
                    .get()
                remoteViews.setImageViewBitmap(R.id.img_widged, bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val extras = bundleOf(EXTRA_NAME to userFav[position].name)
        val fillIntent = Intent()
        fillIntent.putExtras(extras)
        remoteViews.setOnClickFillInIntent(R.id.img_widged, fillIntent)
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}

