package com.singhj.liberary

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    settingsRepository: SettingsRepository,
    onNavigateBack: () -> Unit,
    onThemeChanged: (Theme) -> Unit
) {
    var selectedTheme by remember { mutableStateOf(settingsRepository.getTheme()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("User Name", style = MaterialTheme.typography.titleLarge)
            Text("user.name@email.com", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(32.dp))

            Text("Theme", style = MaterialTheme.typography.titleMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedTheme == Theme.LIGHT,
                    onClick = {
                        selectedTheme = Theme.LIGHT
                        settingsRepository.saveTheme(Theme.LIGHT)
                        onThemeChanged(Theme.LIGHT)
                    }
                )
                Text("Light")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedTheme == Theme.DARK,
                    onClick = {
                        selectedTheme = Theme.DARK
                        settingsRepository.saveTheme(Theme.DARK)
                        onThemeChanged(Theme.DARK)
                    }
                )
                Text("Dark")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedTheme == Theme.SYSTEM,
                    onClick = {
                        selectedTheme = Theme.SYSTEM
                        settingsRepository.saveTheme(Theme.SYSTEM)
                        onThemeChanged(Theme.SYSTEM)
                    }
                )
                Text("System")
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { authViewModel.logout() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Logout", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}
