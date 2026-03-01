package com.singhj.liberary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = { username = it }, placeholder = { Text("Username") })
        TextField(value = password, onValueChange = { password = it }, placeholder = { Text("Password") })
        Button(onClick = { authViewModel.login(username, password) }) {
            Text("Login")
        }

        when (val state = authState) {
            is AuthState.Success -> {
                onLoginSuccess()
            }
            is AuthState.Error -> {
                Text(text = state.message)
            }
            is AuthState.Loading -> {
                // You can show a loading indicator here
            }
            else -> {}
        }
    }
}