package com.singhj.liberary

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val token: String)

class AuthRepository {
    suspend fun login(username: String, password: String): AuthResponse {
        if (username == "test" && password == "test") {
            return AuthResponse(token = "dummy-token")
        } else {
            throw Exception("Invalid credentials")
        }
    }
}
