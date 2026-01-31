package com.example.soundquest2.domain.repository

import com.example.soundquest2.domain.model.enums.AppTheme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun observe(): Flow<AppTheme>
    suspend fun saveTheme(theme: AppTheme)
    suspend fun getInitialTheme(): AppTheme
}