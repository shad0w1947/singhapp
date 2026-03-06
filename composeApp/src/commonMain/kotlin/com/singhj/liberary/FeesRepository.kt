package com.singhj.liberary

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class FeesStatusResponse(
    val status: String,
    val amount: String? = null,
    val dueDate: String? = null
)

class FeesRepository(
    private val settingsRepository: SettingsRepository
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getFeesStatus(): FeesStatusResponse {
        val token = settingsRepository.getAuthToken()
        // Using 10.0.2.2 for Android Emulator to access host localhost
        // For production, this would be a real URL
        val baseUrl = "http://10.0.2.2:8080" 
        
        return client.get("$baseUrl/fees/card") {
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }
}
