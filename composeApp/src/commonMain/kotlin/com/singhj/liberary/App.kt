package com.singhj.liberary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.russhwolf.settings.Settings

sealed class Screen {
    object Splash : Screen()
    object Auth : Screen()
    object Main : Screen()
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
        val settings = remember { Settings() }
        val settingsRepository = remember { SettingsRepository(settings) }
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
                MainContent()
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

@Composable
fun MainContent() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Start App")
        }
    }
}