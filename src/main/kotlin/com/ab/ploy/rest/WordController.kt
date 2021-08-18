/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Word
import com.ab.ploy.services.WordService
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
}
