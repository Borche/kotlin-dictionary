/* Copyright © 2021 */
package com.ab.ploy.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "words")
public class Word {
  @Id private String id;
  private Language language;
  private String word;
  private WordType type;
  private Map<Language, TranslatedLanguage> translatedLanguages;
}
