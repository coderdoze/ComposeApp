package com.example.examplebroadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ExampleBroadcastReceiver : BroadcastReceiver()  {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "EBR triggered", Toast.LENGTH_SHORT).show();
    }
}