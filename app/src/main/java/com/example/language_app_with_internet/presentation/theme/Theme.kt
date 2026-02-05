package com.example.language_app_with_internet.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.languageapp.data.storage.AppStorage
import org.koin.compose.koinInject

private val DarkColorScheme = darkColorScheme(
    background = dark,
    onBackground = white,
    surface = blue,
    onSurface = white,
    primary = deepBlue,
    onPrimary = white
)

private val LightColorScheme = lightColorScheme(
    background = white,
    onBackground = dark,
    surface = blue,
    onSurface = white,
    primary = deepBlue,
    onPrimary = white
)

@Composable
fun LanguageappwithinternetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val appStorage: AppStorage = koinInject()
    val themePreference by appStorage.isSystemInDarkThemeFlow.collectAsState()

    val isDark = when (themePreference) {
        1 -> true
        -1 -> false
        else -> darkTheme
    }

    val colorScheme = when {
        isDark -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}