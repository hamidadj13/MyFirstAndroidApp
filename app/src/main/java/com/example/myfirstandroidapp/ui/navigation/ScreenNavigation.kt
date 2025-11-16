// package com.example.myfirstandroidapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myfirstandroidapp.R

sealed class ScreenNavigation(
    val route: String,
    @StringRes val titleRes: Int,
    val icon: ImageVector
) {
    object Home     : ScreenNavigation("home",      R.string.home,      Icons.Default.Home)
    object Users    : ScreenNavigation("users",     R.string.users,     Icons.Default.Diversity3)
    object Products : ScreenNavigation("products",  R.string.products,  Icons.Default.ShoppingCart)
    object UsersDb  : ScreenNavigation("db-users",  R.string.db_users,  Icons.Default.Diversity1)
    object Login    : ScreenNavigation("login",     R.string.login,     Icons.Default.Person)
    object Register : ScreenNavigation("register",  R.string.register,  Icons.Default.Contacts)
    object Settings : ScreenNavigation("settings",  R.string.settings,  Icons.Default.Settings)

    companion object {
        fun fromRoute(route: String?): ScreenNavigation {
            return when (route) {
                Home.route     -> Home
                Users.route    -> Users
                Products.route -> Products
                UsersDb.route  -> UsersDb
                Login.route    -> Login
                Register.route -> Register
                Settings.route -> Settings
                else           -> Home // valeur par défaut si route == null ou inconnue
            }
        }
    }
}
