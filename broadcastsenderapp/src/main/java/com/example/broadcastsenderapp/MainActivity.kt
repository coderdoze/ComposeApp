package com.example.broadcastsenderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView:TextView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            sendCustomBroadcast()
        }
    }

    private fun sendCustomBroadcast() {
        val intent = Intent("com.example.EXAMPLE_ACTION")
        intent.setPackage("com.example.broadcastreciever")
        sendBroadcast(intent)
    }

}