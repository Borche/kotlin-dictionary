/* Copyright © 2021 */
package com.ab.ploy.services

import com.ab.ploy.models.Word
import com.ab.ploy.persistence.WordRepository
import org.springframework.stereotype.Service

@Service
class WordService(val wordRepository: WordRepository) {
  fun createWord(word: Word) = wordRepository.create(word)
  fun replaceWord(word: Word) = wordRepository.replace(word)
}
