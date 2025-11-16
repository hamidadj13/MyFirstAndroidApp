package com.example.myfirstandroidapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.shape.RoundedCornerShape

// Fournit des couleurs de bouton globales
val LocalAppButtonColors = staticCompositionLocalOf<ButtonColors> {
    error("No button colors provided")
}

private val DarkColorScheme = darkColorScheme(
    primary = LbcOrange,
    secondary = SecondaryTeal,
    background = DarkBackground,
    surface = DarkBackground,
    onPrimary = LbcOnOrange,
    onSecondary = OnDarkBackground,
    onBackground = OnDarkBackground,
    onSurface = OnDarkBackground
)

private val LightColorScheme = lightColorScheme(
    primary = LbcOrange,
    secondary = SecondaryTeal,
    background = LightBackground,
    surface = LightBackground,
    onPrimary = LbcOnOrange,
    onSecondary = OnLightBackground,
    onBackground = OnLightBackground,
    onSurface = OnLightBackground
)

@Composable
fun MyFirstAndroidAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Couleurs globales pour tous les boutons
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = LbcOrange,
        contentColor = LbcOnOrange,
        disabledContainerColor = LbcOrange.copy(alpha = 0.3f),
        disabledContentColor = LbcOnOrange.copy(alpha = 0.3f)
    )

    CompositionLocalProvider(LocalAppButtonColors provides buttonColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes(
                small = RoundedCornerShape(8),
                medium = RoundedCornerShape(12),
                large = RoundedCornerShape(16)
            ),
            content = content
        )
    }
}
