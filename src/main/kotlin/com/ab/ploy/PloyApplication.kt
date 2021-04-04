package com.ab.ploy

import com.ab.ploy.models.Beer
import com.faunadb.client.FaunaClient
import com.faunadb.client.query.Language.*
import com.faunadb.client.types.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture

@SpringBootApplication
@Controller
class PloyApplication {

    @org.springframework.beans.factory.annotation.Value("\${fauna-db.secret}")
    lateinit var faunaDbSecret: String

    @PostMapping("/beers")
    @ResponseBody
    fun helloWorld(@RequestBody beer: Beer): ResponseEntity<Beer> {
        val client = FaunaClient.builder()
            .withSecret(faunaDbSecret)
            .build();

        val result: Value = client.query(
            Create(
                Collection("beers"),
                Obj("data", Value(beer))
            )
        ).get()

        val beer3: Beer = result.at("data").to(Beer::class.java).get()

        return ResponseEntity.ok(beer3)
    }

    fun createBeers() {
        val client = FaunaClient.builder()
            .withSecret("fnAEF85GlIACAcQc4a-t5MmBVj6v9S7b5XDgpzKJ")
            .build();

        val completableFuture = client.query(
            Map(
                Value(listOf<String>("Budweiser", "Sofiero")),
                Lambda(
                    Value("beer"),
                    Create(
                        Collection("beers"),
                        Obj("data",
                            Obj(
                                "name", Var("beer"),
                                "alcohol", Value("5%")
                            )
                        )
                    )
                )
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<PloyApplication>(*args)
}
