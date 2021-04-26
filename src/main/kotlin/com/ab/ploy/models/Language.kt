/* Copyright © 2021 */
package com.ab.ploy.models

enum class Language {
    SWEDISH,
    ENGLISH,
    SPANISH;

    companion object {
        fun get(language: String) = values().find { it.name.equals(language, ignoreCase = true) }
    }
}
