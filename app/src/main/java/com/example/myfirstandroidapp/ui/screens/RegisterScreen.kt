package com.example.myfirstandroidapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstandroidapp.R
import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import com.example.myfirstandroidapp.ui.theme.LocalAppButtonColors

@Composable
fun RegisterScreen(
    onNavigateLogin: () -> Unit,
    onRegistered: () -> Unit
) {
    val vm: AuthViewModel = viewModel()
    val isAuthenticated by vm.isAuthenticated.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onRegistered()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.register_title),
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.register_email)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.register_password)) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Button(
            onClick = {
                vm.register(email, password) { error = it }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = LocalAppButtonColors.current
        ) {
            Text(text = stringResource(id = R.string.register_button))
        }

        TextButton(
            onClick = onNavigateLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.register_login_prompt))
        }
    }
}
