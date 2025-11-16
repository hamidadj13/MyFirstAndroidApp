package com.example.myfirstandroidapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val screens = listOf(
        ScreenNavigation.Home,
        ScreenNavigation.Users,
        ScreenNavigation.Products,
        ScreenNavigation.UsersDb
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // ----- 🎨 PALETTE ORANGE -----
    val backgroundColor = Color(0xFFFDF8F3)
    val borderColor = Color(0xFFE8E0D9)
    val unselectedColor = Color(0xFFB4A69A)
    val orangePrimary = Color(0xFFFF9800)
    val orangeDark = Color(0xFFF57C00)
    val indicatorColor = orangePrimary.copy(alpha = 0.12f)

    Surface(
        tonalElevation = 4.dp,
        color = backgroundColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier
                .height(80.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(top = 2.dp)
        ) {
            screens.forEach { screen ->
                val selected = currentRoute == screen.route

                val color by animateColorAsState(
                    if (selected) orangeDark else unselectedColor
                )

                val scale by animateFloatAsState(if (selected) 1.15f else 1f)

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 6.dp),   // <-- 🔥 padding top ajouté ici
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = stringResource(id = screen.titleRes),
                            tint = color,
                            modifier = Modifier
                                .shadow(
                                    elevation = if (selected) 8.dp else 0.dp,
                                    shape = CircleShape,
                                    ambientColor = color,
                                    spotColor = color
                                )
                                .scale(scale)
                                .padding(top = 6.dp)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = screen.titleRes),
                            color = color,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = false,
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .scale(scale),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = indicatorColor
                    )
                )
            }
        }
    }
}