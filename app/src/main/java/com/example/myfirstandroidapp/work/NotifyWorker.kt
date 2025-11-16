package com.example.myfirstandroidapp.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myfirstandroidapp.notifications.NotificationHelper

class NotifyWorker (
    appContext: Context,
    params: WorkerParameters
): Worker(appContext, params) {
    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Worker Notification"
        val message = inputData.getString("message") ?: "Task executed"

        Log.d("NotifyWorker", "DoWork is launched")

        val posted = NotificationHelper.show(applicationContext, title, message)
        Log.d("NotifyWorker", "DoWork notification sent $posted")

        return Result.success()
    }
}