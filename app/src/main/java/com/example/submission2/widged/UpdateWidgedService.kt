package com.example.submission2.widged

import android.app.job.JobParameters
import android.app.job.JobService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent

class UpdateWidgedService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        val intent = Intent(this, GithubWidged::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val ids = AppWidgetManager.getInstance(application)
            .getAppWidgetIds(ComponentName(application, GithubWidged::class.java))

        val githubUserWidget = GithubWidged()
        githubUserWidget.onUpdate(this, AppWidgetManager.getInstance(this), ids)
        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean = false
}