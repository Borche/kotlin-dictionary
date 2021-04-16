/* Copyright © 2021 */
package com.ab.ploy.models

import com.faunadb.client.types.FaunaConstructor
import com.faunadb.client.types.FaunaField
import com.faunadb.client.types.FaunaIgnore

class FaunaWord
@FaunaConstructor
constructor(
    @FaunaField var id: String?,
    @FaunaField var language: String?,
    @FaunaField var word: String?,
    @FaunaField var type: String?
) {
  @FaunaField var translatedLanguages: TranslatedLanguages? = null
}

class TranslatedLanguages {
  var swedish: TranslatedLanguage? = null
  var english: TranslatedLanguage? = null
  var spanish: TranslatedLanguage? = null
}

class TranslatedLanguage @FaunaConstructor constructor() {
  var translations: List<SimpleWord>? = null
  var propagate: Boolean? = null
    @FaunaIgnore get
}

class SimpleWord {
  var word: String? = null
}
