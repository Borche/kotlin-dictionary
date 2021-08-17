/* Copyright © 2021 */
package com.ab.ploy.model;

import java.util.Arrays;

public enum Language {
  SWEDISH,
  ENGLISH,
  SPANISH;

  public static Language get(String language) {
    return Arrays.stream(values())
        .filter(l -> l.name().equalsIgnoreCase(language))
        .findFirst()
        .orElse(null);
  }
}
