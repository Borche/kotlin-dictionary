/* Copyright © 2021 */
package com.ab.ploy.services

import com.ab.ploy.models.Language
import com.ab.ploy.models.SimpleWord
import com.ab.ploy.models.TranslatedLanguage
import com.ab.ploy.models.Word
import com.ab.ploy.models.WordType
import com.ab.ploy.persistence.WordRepository
import java.util.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.mock

class WordServiceTest() {

    private val wordRepository = mock(WordRepository::class.java)

    private lateinit var wordService: WordService

    @BeforeEach
    fun beforeEach() {
        wordService = WordService(wordRepository)
    }

    @Test
    fun `createWord should propagate SPANISH and ENGLISH`() {
        val word = createWord()
        val savedSwedishWord = createWord(withId = true)
        val savedEnglishWord = createWord(withId = true, Language.ENGLISH)
        val savedSpanishWord = createWord(withId = true, Language.SPANISH)

        Mockito.`when`(wordRepository.save(Mockito.any()))
            .thenReturn(savedSwedishWord)
            .thenReturn(savedEnglishWord)
            .thenReturn(savedSpanishWord)

        val result = wordService.createWord(word)

        Assertions.assertEquals(1, result[Language.SWEDISH])
        Assertions.assertEquals(1, result[Language.ENGLISH])
        Assertions.assertEquals(1, result[Language.SPANISH])
        Mockito.verify(wordRepository, Mockito.times(3)).save(any())
    }

    @Test
    fun `createWord should propagate only SPANISH`() {
        val word = createWord(propagateEnglish = false)
        val savedSwedishWord = createWord(withId = true)
        val savedSpanishWord = createWord(withId = true, Language.SPANISH)

        Mockito.`when`(wordRepository.save(Mockito.any()))
            .thenReturn(savedSwedishWord)
            .thenReturn(savedSpanishWord)

        val result = wordService.createWord(word)

        Assertions.assertEquals(1, result[Language.SWEDISH])
        Assertions.assertEquals(null, result[Language.ENGLISH])
        Assertions.assertEquals(1, result[Language.SPANISH])
        Mockito.verify(wordRepository, Mockito.times(2)).save(any())
    }

    @Test
    fun `createWord should propagate only ENGLISH`() {
        val word = createWord(propagateSpanish = false)
        val savedSwedishWord = createWord(withId = true)
        val savedEnglishWord = createWord(withId = true, Language.ENGLISH)

        Mockito.`when`(wordRepository.save(Mockito.any()))
            .thenReturn(savedSwedishWord)
            .thenReturn(savedEnglishWord)

        val result = wordService.createWord(word)

        Assertions.assertEquals(1, result[Language.SWEDISH])
        Assertions.assertEquals(1, result[Language.ENGLISH])
        Assertions.assertEquals(null, result[Language.SPANISH])
        Mockito.verify(wordRepository, Mockito.times(2)).save(any())
    }

    @Test
    fun `createWord should not propagate anything`() {
        val word = createWord(propagateSpanish = false, propagateEnglish = false)
        val savedSwedishWord = createWord(withId = true)

        Mockito.`when`(wordRepository.save(Mockito.any())).thenReturn(savedSwedishWord)

        val result = wordService.createWord(word)

        Assertions.assertEquals(1, result[Language.SWEDISH])
        Assertions.assertEquals(null, result[Language.ENGLISH])
        Assertions.assertEquals(null, result[Language.SPANISH])
        Mockito.verify(wordRepository, Mockito.times(1)).save(any())
    }

    private fun createWord(
        withId: Boolean = false,
        language: Language = Language.SWEDISH,
        propagateSpanish: Boolean = true,
        propagateEnglish: Boolean = true
    ): Word {
        val tl1 = TranslatedLanguage(listOf(SimpleWord("Speak")), propagateEnglish)
        val tl2 = TranslatedLanguage(listOf(SimpleWord("Hablar")), propagateSpanish)
        val tls = mutableMapOf(Pair(Language.ENGLISH, tl1), Pair(Language.SPANISH, tl2))

        var id: String? = null
        if (withId) id = UUID.randomUUID().toString()

        return Word(id, language, "Prata", WordType.NOUN, tls)
    }
}
