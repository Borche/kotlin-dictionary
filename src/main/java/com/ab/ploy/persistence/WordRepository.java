/* Copyright © 2021 */
package com.ab.ploy.persistence;

import com.ab.ploy.model.Word;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {}
