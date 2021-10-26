/* Copyright © 2021 */
package com.ab.ploy.admin.service

import com.ab.ploy.admin.persistence.WordRepository
import com.ab.ploy.common.models.Language
import com.ab.ploy.common.models.SimpleWord
import com.ab.ploy.common.models.TranslatedLanguage
import com.ab.ploy.common.models.Word
import org.springframework.stereotype.Service

@Service
class WordService(private val wordRepository: WordRepository) {


    fun getWord(word: String) = wordRepository.findByWord(word)

    fun getWords(): MutableList<Word> = wordRepository.findAll()

    fun createWord(word: Word): Map<Language, Int> {
        val savedWord = wordRepository.save(word)

        val propagatedWords = propagate(word)

        val allSavedWords = mutableListOf(savedWord)

        propagatedWords.forEach { allSavedWords.add(wordRepository.save(it)) }

        return allSavedWords.map { it.language to 1 }.toMap()
    }

    fun propagate(word: Word): List<Word> {
        val swedish =
            propagateLanguage(
                word, word.translatedLanguages?.get(Language.SWEDISH), Language.SWEDISH)
        val english =
            propagateLanguage(
                word, word.translatedLanguages?.get(Language.ENGLISH), Language.ENGLISH)
        val spanish =
            propagateLanguage(
                word, word.translatedLanguages?.get(Language.SPANISH), Language.SPANISH)

        return listOfNotNull(swedish, english, spanish)
    }

    fun propagateLanguage(
        word: Word,
        translatedLanguage: TranslatedLanguage?,
        language: Language
    ): Word? {
        if (translatedLanguage == null) return null
        if (translatedLanguage.propagate != true) return null
        if (word.language == language) return null // Don't propagate to the same language

        val translatedLanguages =
            mutableMapOf(Pair(word.language, TranslatedLanguage(listOf(SimpleWord(word.word)))))

        word.translatedLanguages?.filterKeys { it != language }?.forEach {
            translatedLanguages[it.key] =
                TranslatedLanguage(listOf(SimpleWord(it.value.translations!![0].word)))
        }

        return Word(
            null,
            language,
            translatedLanguage.translations?.get(0)?.word!!,
            word.type,
            translatedLanguages)
    }
}
