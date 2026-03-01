package com.singhj.liberary

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class SettingsRepository(private val settings: Settings) {

    fun saveToken(token: String) {
        settings["auth_token"] = token
    }

    fun getToken(): String? {
        return settings.getStringOrNull("auth_token")
    }

    fun clearToken() {
        settings.remove("auth_token")
    }
}