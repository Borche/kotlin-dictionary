/* Copyright © 2021 */
package com.ab.ploy.admin.persistence

import com.ab.ploy.common.models.Word
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface WordRepository : MongoRepository<Word, String> {
    fun findByWord(word: String): Word
}
