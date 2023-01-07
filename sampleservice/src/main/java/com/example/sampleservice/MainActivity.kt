package com.example.sampleservice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener {
            scheduleJob()
        }
        binding.stopButton.setOnClickListener {
            cancelJob()
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

    companion object{
        const val TAG = "MainActivity"
        const val jobID = 123
    }
}