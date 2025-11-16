package com.example.myfirstandroidapp

import android.app.Application
import android.util.Log
import com.example.myfirstandroidapp.notifications.NotificationHelper
import com.google.firebase.auth.FirebaseAuth

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        NotificationHelper.createChannel(this)

        /*FirebaseAuth.getInstance().signInAnonymously()
            .addOnSuccessListener { Log.d("AUTH", "Signed successfully: uid =${it.user?.uid}") }
            .addOnFailureListener{ Log.e("AUTH", "Signed failed", it) }*/
    }
}