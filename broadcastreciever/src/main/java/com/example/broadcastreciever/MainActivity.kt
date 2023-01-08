package com.example.broadcastreciever

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private val orderedReceiver1 = OrderedReceiver1()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFiler = IntentFilter("com.example.EXAMPLE_ACTION")
        registerReceiver(orderedReceiver1,intentFiler)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(orderedReceiver1)
    }
}