/* Copyright © 2021 */
package com.ab.ploy.persistence.fauna

import com.ab.ploy.models.FaunaWord
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language.Arr
import com.faunadb.client.query.Language.Call
import com.faunadb.client.query.Language.Collection
import com.faunadb.client.query.Language.Create
import com.faunadb.client.query.Language.Function
import com.faunadb.client.query.Language.Get
import com.faunadb.client.query.Language.Index
import com.faunadb.client.query.Language.Match
import com.faunadb.client.query.Language.Obj
import com.faunadb.client.query.Language.Replace
import com.faunadb.client.query.Language.Select
import com.faunadb.client.query.Language.Value
import com.faunadb.client.types.Value
import org.springframework.stereotype.Repository

@Repository
class FaunaWordRepository(val client: FaunaClient) {
  companion object {
    const val WORD_COLLECTION_NAME = "words"
  }

  fun create(word: FaunaWord): FaunaWord {
    val result: Value =
        client
            .query(
                Call(
                    Function("MergeIdAndData"),
                    Create(Collection(WORD_COLLECTION_NAME), Obj("data", Value(word)))))
            .get()

    return result.to(FaunaWord::class.java).get()
  }

  fun replace(word: FaunaWord): FaunaWord {
    val result: Value =
        client
            .query(
                Call(
                    Function("MergeIdAndData"),
                    Replace(
                        Select(
                            Value("ref"),
                            Get(
                                Match(
                                    Index("word_by_word_and_language"),
                                    Arr(Value(word.word), Value(word.language))))),
                        Obj("data", Value(word)))))
            .get()

    return result.to(FaunaWord::class.java).get()
  }
}
