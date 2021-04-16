/* Copyright © 2021 */
package com.ab.ploy.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "words")
data class Word(@Id val id: String?, val language: String, val word: String, val type: String)
