// MainScreen.kt
package com.example.myfirstandroidapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfirstandroidapp.R
import com.example.myfirstandroidapp.ui.auth.AuthViewModel
import com.example.myfirstandroidapp.ui.components.AppTopBar
import com.example.myfirstandroidapp.ui.components.BottomNavigationBar
import com.example.myfirstandroidapp.ui.navigation.AppNavGraph
import com.example.myfirstandroidapp.ui.theme.MyFirstAndroidAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = ScreenNavigation.fromRoute(backStackEntry?.destination?.route)

    val ctx = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    val isAuthed by authViewModel.isAuthenticated.collectAsState()
    val scope = rememberCoroutineScope()

    // sheet state + visibility flag
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSettingsSheet by remember { mutableStateOf(false) }

    // when showSettingsSheet changes, show or hide the sheet
    LaunchedEffect(showSettingsSheet) {
        if (showSettingsSheet) sheetState.show() else sheetState.hide()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(id = currentDestination.titleRes),
                actions = {
                    IconButton(onClick = { showSettingsSheet = true }) {
                        Icon(
                            imageVector = ScreenNavigation.Settings.icon,
                            contentDescription = stringResource(id = R.string.settings_action_description)
                        )
                    }
                }
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AppNavGraph(navController = navController)
        }
    }

    // ModalBottomSheet displayed above the rest of UI when showSettingsSheet = true
    if (showSettingsSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSettingsSheet = false },
            sheetState = sheetState,
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            SettingsContent(
                ctx = ctx,
                isAuthed = isAuthed,
                navController = navController,
                authViewModel = authViewModel,
                onClose = { showSettingsSheet = false }
            )
        }
    }
}

@Preview(showBackground = true, name = "MainScreen Preview")
@Composable
fun MainScreenPreview() {
    MyFirstAndroidAppTheme {
        MainScreen()
    }
}
