/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.ab.ploy.models.Beer
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language.Call
import com.faunadb.client.query.Language.Collection
import com.faunadb.client.query.Language.Create
import com.faunadb.client.query.Language.Delete
import com.faunadb.client.query.Language.Documents
import com.faunadb.client.query.Language.Function
import com.faunadb.client.query.Language.Get
import com.faunadb.client.query.Language.Index
import com.faunadb.client.query.Language.Lambda
import com.faunadb.client.query.Language.Map
import com.faunadb.client.query.Language.Match
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
    const val BEER_COLLECTION_NAME = "beers"
  }

  /** Get all beers with their ids merged into the beer. */
  fun getAll(): MutableCollection<Beer>? {
    return client
        .query(
            SelectAsIndex(
                Value("data"),
                Map(
                    Paginate(Documents(Collection(BEER_COLLECTION_NAME))),
                    Lambda("doc", Call(Function("GetMergeIdAndData"), Var("doc"))))))
        .thenApply { it.asCollectionOf(Beer::class.java).get() }
        .get()
        .toMutableList()
  }

  /** Create a new beer and return the beer with its id merged into it. */
  fun create(beer: Beer): Beer {
    val result: Value =
        client
            .query(
                Call(
                    Function("MergeIdAndData"),
                    Create(Collection(BEER_COLLECTION_NAME), Obj("data", Value(beer)))))
            .get()

    return result.to(Beer::class.java).get()
  }

  fun delete(name: String): Beer? {
    val result: Value =
        client
            .query(
                Delete(Select(Value("ref"), Get(Match(Index(Value("beer_by_name")), Value(name))))))
            .get()

    return result.at("data").to(Beer::class.java).get()
  }
}
