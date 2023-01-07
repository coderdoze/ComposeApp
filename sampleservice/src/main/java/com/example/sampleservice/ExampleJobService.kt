package com.example.sampleservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class ExampleJobService : JobService() {

    private var isCancelled=false

    //returns true if we want our job to continue running, using a
    //separate thread when appropriate.
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job started")
        startBackgroundWork(params)
        return true
    }

    //true to indicate to the JobManager whether you'd like to reschedule this job
    // based on the retry criteria provided at job creation-time; or false to end the job entirely.
    //Note: But we are responsible for stopping out background work

    //we want to restart job in this case on appropriate conditions
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job finished before completion ")
        isCancelled = true
        return true
    }

    private fun startBackgroundWork(params: JobParameters?){
       Thread(Runnable {
                for(i in 1..10){
                    Log.d(TAG, "run: $i")
                    if (isCancelled) return@Runnable

                    try {
                        Thread.sleep(1000)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            Log.d(TAG, "Job finished")
            jobFinished(params,false)
           }
        ).start()
    }

    companion object{
        const val TAG = "ExampleJobService"
    }
}