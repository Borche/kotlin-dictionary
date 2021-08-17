/* Copyright © 2021 */
package com.ab.ploy.model;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;

@Data
public class TranslatedLanguage {
  private List<SimpleWord> translations;

  @Transient private Boolean propagate;

  @PersistenceConstructor
  public TranslatedLanguage(List<SimpleWord> translations) {
    this.translations = translations;
  }
}
