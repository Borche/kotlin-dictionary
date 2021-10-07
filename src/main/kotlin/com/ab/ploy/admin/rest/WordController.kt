/* Copyright © 2021 */
package com.ab.ploy.admin.rest

import com.ab.ploy.admin.service.WordService
import com.ab.ploy.common.models.Word
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/words")
class WordController(val wordService: WordService) {

    @GetMapping fun getWords() = wordService.getWords()

    @PostMapping fun createWord(@RequestBody word: Word) = wordService.createWord(word)

    @GetMapping("/hehe") fun lawl() = "tjenare2"
}
