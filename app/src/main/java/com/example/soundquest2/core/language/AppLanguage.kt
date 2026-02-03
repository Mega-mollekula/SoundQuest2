package com.example.soundquest2.core.language

enum class AppLanguage(val code: String) {
    RU("ru"),
    EN("en");

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.firstOrNull { it.code == code } ?: RU
        }
    }
}