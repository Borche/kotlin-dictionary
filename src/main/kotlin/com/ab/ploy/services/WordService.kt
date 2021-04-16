/* Copyright © 2021 */
package com.ab.ploy.services

import com.ab.ploy.models.Word
import com.ab.ploy.persistence.WordRepository
import org.springframework.stereotype.Service

@Service
class WordService(private val wordRepository: WordRepository) {
  fun getWords(): MutableList<Word> = wordRepository.findAll()
  fun createWord(word: Word): Word = wordRepository.save(word)
}
