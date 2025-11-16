package com.example.myfirstandroidapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstandroidapp.ui.users.UserDbViewModel

@Composable
fun UsersDbScreen() {

    val vm: UserDbViewModel = viewModel()
    val users by vm.users.collectAsState()

    // COULEURS
    val orangePrimary = Color(0xFFFF6B00)
    val redDanger = Color(0xFFD32F2F)

    Scaffold { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                // ADD USER — ORANGE
                Button(
                    onClick = {
                        if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
                            vm.addUser(firstName, lastName, email)
                            firstName = ""; lastName = ""; email = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orangePrimary,
                        contentColor = Color.White
                    )
                ) {
                    Text("Add User")
                }

                // CLEAR DB — ROUGE
                OutlinedButton(
                    onClick = { vm.clearAll() },
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = redDanger
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(redDanger)
                    )
                ) {
                    Text("Clear DB")
                }
            }

            LazyColumn(Modifier.fillMaxSize()) {
                items(users, key = { it.localId }) { user ->
                    ElevatedCard(Modifier.fillMaxWidth()) {
                        Row(Modifier.padding(12.dp)) {
                            Column(Modifier.weight(1f)) {
                                Text(user.firstName, style = MaterialTheme.typography.titleMedium)
                                Text(user.lastName, style = MaterialTheme.typography.titleMedium)
                                Text(user.email, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }

        }
    }
}