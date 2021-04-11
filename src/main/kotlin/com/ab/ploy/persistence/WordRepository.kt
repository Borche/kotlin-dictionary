/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.ab.ploy.models.Word
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language.Call
import com.faunadb.client.query.Language.Collection
import com.faunadb.client.query.Language.Create
import com.faunadb.client.query.Language.Function
import com.faunadb.client.query.Language.Obj
import com.faunadb.client.query.Language.Value
import com.faunadb.client.types.Value
import org.springframework.stereotype.Repository

@Repository
class WordRepository(val client: FaunaClient) {
    companion object {
        const val WORD_COLLECTION_NAME = "words"
    }

    fun create(word: Word): Word {
        val result: Value = client.query(
            Call(
                Function("MergeIdAndData"),
                Create(
                    Collection(WORD_COLLECTION_NAME),
                    Obj("data", Value(word))
                )
            )
        ).get()

        return result.to(Word::class.java).get()
    }
}
