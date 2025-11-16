package com.example.myfirstandroidapp.ui.screens

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myfirstandroidapp.R
import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import com.example.myfirstandroidapp.ui.components.LanguageDropDown
import com.example.myfirstandroidapp.work.enqueueOneTimeAfter10Sec
import com.example.myfirstandroidapp.work.enqueueOneTimeWifiCharging

@Composable
fun SettingsContent(
    ctx: Context,
    isAuthed: Boolean,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onClose: () -> Unit
) {
    var showLogoutConfirm by remember { mutableStateOf(false) }

    // Back button closes the sheet
    BackHandler(enabled = true) {
        onClose()
    }

    // Orange Leboncoin
    val orangePrimary = Color(0xFFFF6B00)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = stringResource(id = R.string.settings_title),
            style = MaterialTheme.typography.headlineSmall
        )

        // Notifications section
        Text(
            text = stringResource(R.string.notifications_section),
            style = MaterialTheme.typography.titleMedium
        )

        // Buttons in ORANGE
        Button(
            onClick = { enqueueOneTimeAfter10Sec(ctx) },
            colors = ButtonDefaults.buttonColors(
                containerColor = orangePrimary,
                contentColor = Color.White
            )
        ) {
            Text("One time action - 10 sec")
        }

        Button(
            onClick = { enqueueOneTimeWifiCharging(ctx) },
            colors = ButtonDefaults.buttonColors(
                containerColor = orangePrimary,
                contentColor = Color.White
            )
        ) {
            Text("One time action - Wifi & Charging")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

        // Language section
        Text(
            text = stringResource(R.string.choose_language),
            style = MaterialTheme.typography.titleMedium
        )
        LanguageDropDown()

        Spacer(modifier = Modifier.height(40.dp))

        // 🔥 Logout button — **RED**
        if (isAuthed) {
            Button(
                onClick = { showLogoutConfirm = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.logout))
            }
        }
    }

    // Confirmation dialog
    if (showLogoutConfirm) {
        AlertDialog(
            onDismissRequest = { showLogoutConfirm = false },
            title = { Text(text = stringResource(id = R.string.logout)) },
            text = { Text(text = stringResource(id = R.string.logout_confirm_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        authViewModel.logout()
                        showLogoutConfirm = false
                        onClose()

                        // Navigate home and clear backstack
                        navController.navigate(ScreenNavigation.Home.route) {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutConfirm = false }) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            }
        )
    }
}
