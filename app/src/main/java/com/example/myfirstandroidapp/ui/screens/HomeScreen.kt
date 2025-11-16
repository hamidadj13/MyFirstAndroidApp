package com.example.myfirstandroidapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myfirstandroidapp.R
import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import com.example.myfirstandroidapp.ui.theme.LocalAppButtonColors

@Composable
fun HomeScreen(
    navController: NavController
) {
    val ctx = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    val isAuthed by authViewModel.isAuthenticated.collectAsState()
    val user = authViewModel.currentUser

    // Toast si connecté
    LaunchedEffect(isAuthed) {
        if (isAuthed && user?.email != null) {
            Toast.makeText(
                ctx,
                ctx.getString(R.string.home_toast_welcome, user.email),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            if (!isAuthed) {

                // TITRE
                Text(
                    text = stringResource(id = R.string.home_title),
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                // DESCRIPTION
                Text(
                    text = stringResource(id = R.string.home_description),
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(modifier = Modifier.height(32.dp))

                // BTN LOGIN
                Button(
                    onClick = {
                        navController.navigate(ScreenNavigation.Login.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = LocalAppButtonColors.current
                ) {
                    Text(stringResource(id = R.string.home_btn_login))
                }

                // BTN REGISTER
                Button(
                    onClick = {
                        navController.navigate(ScreenNavigation.Register.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    colors = LocalAppButtonColors.current
                ) {
                    Text(stringResource(id = R.string.home_btn_register))
                }

            } else {

                // TITRE POUR CONNECTÉ
                Text(
                    text = stringResource(
                        id = R.string.home_connected_title,
                        user?.email ?: ""
                    ),
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                // DESCRIPTION CONNECTÉ
                Text(
                    text = stringResource(id = R.string.home_connected_description),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
