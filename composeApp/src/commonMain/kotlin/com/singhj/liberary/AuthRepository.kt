package com.singhj.liberary

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val token: String)

@Serializable
data class LoginRequest(val username: String, val password: String)

class AuthRepository {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun login(username: String, password: String): AuthResponse {
        return httpClient.post("http://10.0.2.2:8080/login") { // Using 10.0.2.2 for Android emulator to connect to localhost
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username, password))
        }.body()
    }
}