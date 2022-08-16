package com.example.submission2.widged

import android.content.Intent
import android.widget.RemoteViewsService

class GitWidgedService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory =
        GithubRemoteViewsFactory(this.applicationContext)
}