/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Word
import com.ab.ploy.services.WordService
import com.faunadb.client.errors.BadRequestException
import com.faunadb.client.errors.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/words")
class WordController(val wordService: WordService) {

    @PostMapping
    fun createWord(@RequestBody word: Word): Word {
        return wordService.createWord(word)
    }

    @PutMapping
    fun replaceWord(@RequestBody word: Word): Word {
        return wordService.replaceWord(word)
    }

    /*
     * -- Exception handling --
     */

    @ExceptionHandler(BadRequestException::class)
    fun instanceNotUnique(e: BadRequestException): ResponseEntity<ErrorResponse> {
        if (e.message?.contains("unique") == true)
            return ResponseEntity.badRequest()
                .body(ErrorResponse("Combination of word and language already exists"))
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    @ExceptionHandler(NotFoundException::class)
    fun instanceNotFound(e: NotFoundException) =
        ResponseEntity.badRequest().body(ErrorResponse("Word does not exist"))
}
