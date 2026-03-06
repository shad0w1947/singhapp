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

    val isDarkTheme = if (theme == Theme.SYSTEM) isSystemInDarkTheme() else theme == Theme.DARK
    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        SetSystemBarsTransparent()

        val authRepository = remember { AuthRepository() }
        val authViewModel = remember { AuthViewModel(authRepository, settingsRepository) }
        
        // Initialize Fees Repository and ViewModel for Main Screen
        val feesRepository = remember { FeesRepository(settingsRepository) }
        val mainViewModel = remember { MainViewModel(feesRepository) }

        var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
        val authState by authViewModel.authState.collectAsState()

        LaunchedEffect(authState) {
            if (authState is AuthState.LoggedOut) {
                currentScreen = Screen.Auth
            }
        }

        when (currentScreen) {
            is Screen.Splash -> {
                SplashScreen()
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
                    onLoginSuccess = { 
                        currentScreen = Screen.Main
                        mainViewModel.fetchFeesStatus() // Refresh fees on login
                    }
                )
            }
            is Screen.Main -> {
                MainContent(
                    viewModel = mainViewModel,
                    onProfileClick = { currentScreen = Screen.Profile }
                )
            }
            is Screen.Profile -> {
                ProfileScreen(
                    authViewModel = authViewModel,
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
