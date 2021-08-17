/* Copyright © 2021 */
package com.ab.ploy.service;

import com.ab.ploy.model.Language;
import com.ab.ploy.model.SimpleWord;
import com.ab.ploy.model.TranslatedLanguage;
import com.ab.ploy.model.Word;
import com.ab.ploy.persistence.WordRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class WordService {

  private final WordRepository wordRepository;

  public WordService(WordRepository wordRepository) {
    this.wordRepository = wordRepository;
  }

  public List<Word> getWords() {
    return wordRepository.findAll();
  }

  public List<Word> createWord(Word word) {
    Word savedWord = wordRepository.save(word);

    List<Word> propagatedWords = propagate(word);

    List<Word> allSavedWords = new ArrayList<>();
    allSavedWords.add(savedWord);
    allSavedWords.addAll(propagatedWords);

    return allSavedWords;
  }

  private List<Word> propagate(Word word) {
    List<Word> result = new ArrayList<>();

    for (Language language : Language.values()) {
      Word propagatedWord =
          propagateLanguage(word, word.getTranslatedLanguages().get(language), language);
      if (propagatedWord != null) {
        result.add(word);
      }
    }

    return result;
  }

  private Word propagateLanguage(
      Word word, TranslatedLanguage translatedLanguage, Language language) {
    if (translatedLanguage == null) return null;
    if (translatedLanguage.getPropagate() != Boolean.TRUE) return null;
    if (word.getLanguage() == language) return null;

    Map<Language, TranslatedLanguage> translatedLanguages = new HashMap<>();
    translatedLanguages.put(
        word.getLanguage(), new TranslatedLanguage(List.of(new SimpleWord(word.getWord()))));

    word.getTranslatedLanguages().keySet().stream()
        .filter(l -> l != language)
        .forEach(
            l ->
                translatedLanguages.put(
                    l,
                    new TranslatedLanguage(
                        List.of(
                            new SimpleWord(
                                word.getTranslatedLanguages()
                                    .get(l)
                                    .getTranslations()
                                    .get(0)
                                    .getWord())))));

    return new Word(
        null,
        language,
        translatedLanguage.getTranslations().get(0).getWord(),
        word.getType(),
        translatedLanguages);
  }
}
