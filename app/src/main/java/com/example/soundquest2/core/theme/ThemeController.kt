package com.example.soundquest2.core.theme

import com.example.soundquest2.data.local.storage.ThemeStorage

class ThemeController(
    private val storage: ThemeStorage
) {

    suspend fun getCurrentTheme(): AppTheme {
        return storage.getTheme()
    }

    suspend fun changeTheme(theme: AppTheme) {
        storage.setTheme(theme)
    }
}
