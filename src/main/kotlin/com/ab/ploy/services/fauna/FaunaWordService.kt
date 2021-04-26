/* Copyright © 2021 */
package com.ab.ploy.services.fauna

import com.ab.ploy.models.fauna.FaunaWord
import com.ab.ploy.persistence.fauna.FaunaWordRepository
import org.springframework.stereotype.Service

@Service
class FaunaWordService(val wordRepository: FaunaWordRepository) {
    fun createWord(word: FaunaWord) = wordRepository.create(word)
    fun replaceWord(word: FaunaWord) = wordRepository.replace(word)
}
