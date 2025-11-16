package com.example.myfirstandroidapp.work

import android.content.Context
import androidx.work.*
import java.time.Duration

fun enqueueOneTimeAfter10Sec(context: Context){
    val request = OneTimeWorkRequestBuilder<NotifyWorker>()
        .setInitialDelay(Duration.ofSeconds(10))
        .setInputData(workDataOf(
            "title" to "Test worker",
            "message" to "Task exécuted after 10 seconds"
        )).build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "oneTime10s",
        ExistingWorkPolicy.REPLACE,
        request
    )
}

fun enqueueOneTimeWifiCharging(context: Context){
    val wifiAndCharging = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()

    val request = OneTimeWorkRequestBuilder<NotifyWorker>()
        .setConstraints(wifiAndCharging)
        .setInputData(workDataOf(
            "title" to "Test worker",
            "message" to "Task exécuted with contraint"
        )).build()

    WorkManager.getInstance(context).enqueueUniqueWork(
        "oneTimeWifiCharging",
        ExistingWorkPolicy.REPLACE,
        request
    )
}