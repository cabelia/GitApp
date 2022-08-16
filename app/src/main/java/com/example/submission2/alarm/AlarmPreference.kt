package com.example.submission2.alarm

import android.content.Context

class AlarmPreference(context: Context) {
    companion object {
        const val PREFS_NAME = "remind_pref"
        private const val REMINDER = "isRemind"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setAlarm(value: SetReminders) {
        val edits = preference.edit()
        edits.putBoolean(REMINDER, value.setReminders)
        edits.apply()
    }

    fun getReminder(): SetReminders {
        val model = SetReminders()
        model.setReminders = preference.getBoolean(REMINDER, false)
        return model
    }
}
