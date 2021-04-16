/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.ab.ploy.models.Word
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository interface WordRepository : MongoRepository<Word, String>
