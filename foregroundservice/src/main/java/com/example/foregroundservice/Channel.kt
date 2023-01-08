package com.example.foregroundservice

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class Channel : Application() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChanel()
    }

    private fun createNotificationChanel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val myChannel = NotificationChannel(
                channelId, "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(myChannel)
        }
    }

    companion object{
        const val channelId = "foregroundServiceChannel"
    }
}