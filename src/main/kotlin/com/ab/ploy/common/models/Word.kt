/* Copyright © 2021 */
package com.ab.ploy.common.models

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "words")
data class Word(
    @Id val id: String?,
    val language: Language,
    val word: String,
    val type: WordType,
    val translatedLanguages: Map<Language, TranslatedLanguage>?
)

data class TranslatedLanguage(
    val translations: List<SimpleWord>?,
    @Transient val propagate: Boolean? = null
) {
    @PersistenceConstructor constructor(translations: List<SimpleWord>?) : this(translations, null)
}

data class SimpleWord(val word: String)
