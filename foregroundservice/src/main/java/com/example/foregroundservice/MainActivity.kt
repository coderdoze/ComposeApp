package com.example.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.foregroundservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        bindings.startButton.setOnClickListener {
            val msg = bindings.editText.text
            val intent = Intent(this,SampleService::class.java)

            intent.putExtra("message",msg.toString())
            ContextCompat.startForegroundService(this,intent)
        }

        bindings.stopButton.setOnClickListener {
            val intent = Intent(this,SampleService::class.java)
            stopService(intent)
        }
    }
}