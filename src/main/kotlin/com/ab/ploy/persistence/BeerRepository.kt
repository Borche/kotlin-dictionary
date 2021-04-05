/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.ab.ploy.models.Beer
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language.Collection
import com.faunadb.client.query.Language.Create
import com.faunadb.client.query.Language.Documents
import com.faunadb.client.query.Language.Get
import com.faunadb.client.query.Language.Lambda
import com.faunadb.client.query.Language.Map
import com.faunadb.client.query.Language.Obj
import com.faunadb.client.query.Language.Paginate
import com.faunadb.client.query.Language.Select
import com.faunadb.client.query.Language.SelectAsIndex
import com.faunadb.client.query.Language.Value
import com.faunadb.client.query.Language.Var
import com.faunadb.client.types.Value
import org.springframework.stereotype.Repository

@Repository
class BeerRepository(val client: FaunaClient) {
    companion object {
        const val COLLECTION_NAME = "beers"
    }

    fun getAll(): MutableCollection<Beer>? {
        return client
            .query(
                SelectAsIndex(
                    Value("data"),
                    Map(
                        Paginate(Documents(Collection(COLLECTION_NAME))),
                        Lambda("doc", Select(Value("data"), Get(Var("doc"))))
                    )
                )
            )
            .thenApply { it.asCollectionOf(Beer::class.java).get() }
            .get()
    }

    fun create(beer: Beer): Beer {
        val result: Value =
            client.query(
                Create(
                    Collection(COLLECTION_NAME),
                    Obj("data", Value(beer))
                )
            ).get()

        return result.at("data").to(Beer::class.java).get()
    }
}
