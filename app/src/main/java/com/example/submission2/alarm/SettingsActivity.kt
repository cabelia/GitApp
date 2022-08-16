package com.example.submission2.alarm

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.submission2.R
import com.example.submission2.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var reminders: SetReminders
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alarmPreference = AlarmPreference(this)
        binding.switch1.isChecked = alarmPreference.getReminder().setReminders

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                saveReminder(true)
                alarmReceiver.setRepeatingAlarm(
                    this,
                    getString(R.string.type_repeat_alarm),
                    getString(R.string.time),
                    getString(R.string.massage_alarm)
                )
            } else {
                saveReminder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }

        binding.btnSetLanguage.setOnClickListener(this)
    }

    private fun saveReminder(state: Boolean) {
        val reminderPreference = AlarmPreference(this)
        reminders = SetReminders()

        reminders.setReminders = state
        reminderPreference.setAlarm(reminders)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_set_language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
    }
}