package com.example.myfirstandroidapp

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.myfirstandroidapp.notifications.NotificationHelper
import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import com.example.myfirstandroidapp.ui.screens.MainScreen
import com.example.myfirstandroidapp.ui.theme.MyFirstAndroidAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialise le ViewModel
        authViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[AuthViewModel::class.java]

        // Déconnecter l'utilisateur si connecté
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d("MainActivity", "Utilisateur connecté détecté → déconnexion automatique")
            authViewModel.logout() // Appelle FirebaseAuth.signOut() dans le ViewModel
        }

        // Notifications FCM
        NotificationHelper.createChannel(this)
        FirebaseMessaging.getInstance()
            .token
            .addOnSuccessListener { token ->
                Log.d("FCM", "Main Activity =====> FCM TOKEN: $token")
            }
            .addOnFailureListener { exception ->
                Log.d("FCM", "Main Activity =====> TOKEN ERROR: ", exception)
            }

        // Edge to edge
        enableEdgeToEdge()

        // Compose UI
        setContent {
            MyFirstAndroidAppTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.v("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.v("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.v("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("MainActivity", "onDestroy")
    }
}
