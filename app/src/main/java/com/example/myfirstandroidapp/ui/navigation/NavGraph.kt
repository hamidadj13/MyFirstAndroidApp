package com.example.myfirstandroidapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfirstandroidapp.ui.auth.RequireAuth
import com.example.myfirstandroidapp.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.Home.route
    ) {
        composable(ScreenNavigation.Home.route) { HomeScreen(navController = navController) }

        composable(ScreenNavigation.Users.route) { RequireAuth(navController) { UsersScreen() } }

        composable(ScreenNavigation.Products.route) { RequireAuth(navController) { ProductsScreen() } }

        composable(ScreenNavigation.UsersDb.route) { RequireAuth(navController) { UsersDbScreen() } }

        composable(ScreenNavigation.Login.route) {
            LoginScreen(
                onNavigateRegister = { navController.navigate(ScreenNavigation.Register.route) },
                onLoggedIn = {
                    navController.navigate(ScreenNavigation.Home.route) {
                        popUpTo(ScreenNavigation.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(ScreenNavigation.Register.route) {
            RegisterScreen(
                onNavigateLogin = { navController.navigate(ScreenNavigation.Login.route) },
                onRegistered = {
                    navController.navigate(ScreenNavigation.Home.route) {
                        popUpTo(ScreenNavigation.Register.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
