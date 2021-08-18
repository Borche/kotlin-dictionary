/* Copyright © 2021 */
package com.ab.ploy.rest

import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.mongodb.MongoWriteException
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionMapper {

    private val log = LoggerFactory.getLogger(ExceptionMapper::class.java)

    @ExceptionHandler(Throwable::class)
    fun handleAll(t: Throwable, request: WebRequest): ResponseEntity<ErrorResponse> {
        log.error(t.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse("Ajdå"))
    }

    @ExceptionHandler(MongoWriteException::class, DuplicateKeyException::class)
    fun handleMongoWriteException(
        e: MongoWriteException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        log.error(e.message)
        if (e.message?.contains("duplicate key error") == true) {
            return ResponseEntity.badRequest()
                .body(ErrorResponse("Word already exists for that language"))
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse("Could not write to database"))
    }

    @ExceptionHandler(InvalidFormatException::class)
    fun handleInvalidFormat(
        e: InvalidFormatException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        log.error(e.message)
        return ResponseEntity.badRequest().body(ErrorResponse("Invalid value or format"))
    }
}
