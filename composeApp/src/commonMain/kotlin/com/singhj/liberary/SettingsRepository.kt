package com.singhj.liberary

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

enum class Theme {
    LIGHT, DARK, SYSTEM
}

class SettingsRepository(private val settings: Settings) {

    fun saveAuthToken(token: String) {
        settings["authToken"] = token
    }

    fun getAuthToken(): String? {
        return settings["authToken"]
    }

    fun clearAuthToken() {
        settings.remove("authToken")
    }

    fun saveTheme(theme: Theme) {
        settings["theme"] = theme.name
    }

    fun getTheme(): Theme {
        return when (settings.getStringOrNull("theme")) {
            Theme.LIGHT.name -> Theme.LIGHT
            Theme.DARK.name -> Theme.DARK
            else -> Theme.SYSTEM
        }
    }
}