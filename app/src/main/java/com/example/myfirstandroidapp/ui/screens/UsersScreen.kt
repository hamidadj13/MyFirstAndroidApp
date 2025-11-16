package com.example.myfirstandroidapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirstandroidapp.ui.users.UsersViewModel
import com.example.myfirstandroidapp.ui.users.UserCard
import androidx.compose.animation.animateContentSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen() {
    val vm: UsersViewModel = viewModel()
    val state by vm.state

    val orangePrimary = Color(0xFFFF9800)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EFE8)) // fond doux
            .padding(16.dp)
    ) {

        when {
            state.isLoading -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = orangePrimary)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Loading users...", color = Color.Gray)
                }
            }

            state.error != null -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = { vm.load() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = orangePrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Try Again")
                    }
                }
            }

            else -> {
                val visible = state.all.take(state.visibleCount)

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {

                    itemsIndexed(visible, key = { _, u -> u.id }) { index, user ->

                        UserCard(
                            user = user,
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateContentSize() // animation douce des changements
                        )

                        if (index == visible.lastIndex) {
                            LaunchedEffect(visible.size) { vm.loadMore() }
                        }
                    }

                    if (state.visibleCount < state.all.size) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 10.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LinearProgressIndicator(
                                    modifier = Modifier.fillMaxWidth(),
                                    color = orangePrimary
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    "Loading more...",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}