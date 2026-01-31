package com.example.soundquest2.core.language

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

class AppLocaleController(
    private val context: Context,
) {

    fun applyLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}