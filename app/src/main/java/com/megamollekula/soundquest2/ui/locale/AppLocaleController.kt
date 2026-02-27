package com.megamollekula.soundquest2.ui.locale

import android.content.Context
import android.content.res.Configuration
import com.megamollekula.soundquest2.domain.model.enums.AppLanguage
import java.util.Locale

class AppLocaleController(
    private val context: Context,
) {

    fun apply(language: AppLanguage) {
        val locale = Locale(language.code)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(
            config,
            context.resources.displayMetrics
        )
    }
}