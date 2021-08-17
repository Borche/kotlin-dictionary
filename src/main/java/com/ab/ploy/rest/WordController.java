/* Copyright © 2021 */
package com.ab.ploy.rest;

import com.ab.ploy.model.Language;
import com.ab.ploy.model.Word;
import com.ab.ploy.service.WordService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordController {

  private final WordService wordService;

  public WordController(WordService wordService) {
    this.wordService = wordService;
  }

  @GetMapping
  public List<Word> getWords() {
    return wordService.getWords();
  }

  @PostMapping
  public Map<Language, String> createWord(@RequestBody Word word) {
    return wordService.createWord(word).stream()
        .collect(Collectors.toMap(Word::getLanguage, Word::getWord));
  }
}
