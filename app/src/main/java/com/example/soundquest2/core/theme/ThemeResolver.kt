package com.example.soundquest2.core.theme

import com.example.soundquest2.ui.theme.DarkThemeProperties
import com.example.soundquest2.ui.theme.LightThemeProperties
import com.example.soundquest2.ui.theme.NewYearThemeProperties
import com.example.soundquest2.ui.theme.common.CustomThemeProperties

class ThemeResolver {

    fun resolve(theme: AppTheme): CustomThemeProperties {
        return when (theme) {
            AppTheme.LIGHT -> LightThemeProperties
            AppTheme.DARK -> DarkThemeProperties
            AppTheme.NEW_YEAR -> NewYearThemeProperties
        }
    }
}
