package com.singhj.liberary

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.russhwolf.settings.Settings

sealed class Screen {
    object Splash : Screen()
    object Auth : Screen()
    object Main : Screen()
    object Profile : Screen()
}

@Composable
@Preview
fun App() {
    val settings = remember { Settings() }
    val settingsRepository = remember { SettingsRepository(settings) }
    var theme by remember { mutableStateOf(settingsRepository.getTheme()) }

    val isDarkTheme = when (theme) {
        Theme.LIGHT -> false
        Theme.DARK -> true
        Theme.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        // Set system bars to be transparent at the app level
        SetSystemBarsTransparent()

        var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
        val authRepository = remember { AuthRepository() }
        val authViewModel = remember { AuthViewModel(authRepository, settingsRepository) }

        when (currentScreen) {
            is Screen.Splash -> {
                SplashScreen()
                val authState by authViewModel.authState.collectAsState()
                LaunchedEffect(Unit) {
                    authViewModel.checkForExistingToken()
                }
                when (authState) {
                    is AuthState.Success -> currentScreen = Screen.Main
                    is AuthState.Error -> currentScreen = Screen.Auth
                    else -> {}
                }
            }
            is Screen.Auth -> {
                AuthScreen(
                    authViewModel = authViewModel,
                    onLoginSuccess = { currentScreen = Screen.Main }
                )
            }
            is Screen.Main -> {
                MainContent(onProfileClick = { currentScreen = Screen.Profile })
            }
            is Screen.Profile -> {
                ProfileScreen(
                    settingsRepository = settingsRepository,
                    onNavigateBack = { currentScreen = Screen.Main },
                    onThemeChanged = { newTheme -> theme = newTheme }
                )
            }
        }
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
