package com.singhj.liberary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AuthState {
    object Loading : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val settingsRepository: SettingsRepository
) {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState

    fun checkForExistingToken() {
        val token = settingsRepository.getAuthToken()
        if (token != null) {
            _authState.value = AuthState.Success
        } else {
            _authState.value = AuthState.Error("No token found")
        }
    }

    fun login(token: String) {
        settingsRepository.saveAuthToken(token)
        _authState.value = AuthState.Success
    }
}