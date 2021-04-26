/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Word
import com.ab.ploy.services.WordService
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.mongodb.MongoWriteException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/words")
class WordController(val wordService: WordService) {

    @GetMapping fun getWords() = wordService.getWords()

    @PostMapping fun createWord(@RequestBody word: Word) = wordService.createWord(word)

    /* ---- Exception Handling ---- */

    @ExceptionHandler(MongoWriteException::class)
    fun handleMongoWriteException(e: MongoWriteException): ResponseEntity<ErrorResponse> {
        if (e.message?.contains("duplicate key error") == true) {
            return ResponseEntity.badRequest()
                .body(ErrorResponse("Combination of word and language already exists"))
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormat(e: InvalidFormatException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(ErrorResponse("Invalid value or format"))
    }
}
