package com.example.sampleservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleservice.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener {
            //scheduleJob()
            //useHandler()
            useWorkerThread()
        }
        binding.stopButton.setOnClickListener {
            //cancelJob()
        }
    }

    private fun scheduleJob(){
        val componentName = ComponentName(this, ExampleJobService::class.java)
        val jobInfo = JobInfo.Builder(jobID,componentName)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .build()

        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = jobScheduler.schedule(jobInfo)
        if(resultCode == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job schedule success")
        }else{
            Log.d(TAG, "Job schedule failed")
        }

    }

    private fun cancelJob(){
        val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.cancel(jobID)
        Log.d(TAG, "Job cancelled")
    }

    private fun useHandler(){
        val handlerThread = HandlerThread("myHandlerThread")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val runnable = Runnable {
            for(i in 1..3){
                Log.d(TAG, "useHandler: $i")
                SystemClock.sleep(2000)
                this.runOnUiThread {
                    Toast.makeText(applicationContext,"MSG : $i",Toast.LENGTH_SHORT).show()
                }
            }
        }
        handler.post(runnable)
    }

    private fun useWorkerThread(){
       // thread {
            if (Looper.myLooper() == null)
                Looper.prepare()
            val handler = Handler(Looper.myLooper()!!)
            val runnable = Runnable {
                for (i in 1..4) {
                    Log.d(TAG, "useHandler: ${2 * i}")
                    Toast.makeText(applicationContext, "MSG : ${2*i}", Toast.LENGTH_SHORT).show()

                    SystemClock.sleep(2000)
                    //this.runOnUiThread {
                    //}
                }
            }
            handler.post(runnable)
            Looper.loop()

      //  }
    }

    companion object{
        const val TAG = "MainActivity"
        const val jobID = 123
    }
}