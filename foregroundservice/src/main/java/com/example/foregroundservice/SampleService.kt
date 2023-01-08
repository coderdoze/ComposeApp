package com.example.foregroundservice

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.view.ContentInfoCompat.Flags
import com.example.foregroundservice.Channel.Companion.channelId

class SampleService : Service() {
    // used for bound service
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val msg = intent?.extras?.get("message") as String
        val notificationIntent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Test Notification")
            .setContentText(msg)
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentIntent(pendingIntent)
            .setOngoing(true) // to make notification persistent
            .build()

        startForeground(1,notification)
        return START_NOT_STICKY
    }
}