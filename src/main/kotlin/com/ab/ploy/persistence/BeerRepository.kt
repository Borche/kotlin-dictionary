package com.ab.ploy.persistence

import com.ab.ploy.models.Beer
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language
import com.faunadb.client.types.Value
import org.springframework.stereotype.Repository

@Repository
class BeerRepository(val client: FaunaClient) {

    private val collectionName = "beers"

    fun create(beer: Beer): Beer {
        val result: Value = client.query(
            Language.Create(
                Language.Collection(collectionName),
                Language.Obj("data", Language.Value(beer))
            )
        ).get()

        return result.at("data").to(Beer::class.java).get()
    }
}