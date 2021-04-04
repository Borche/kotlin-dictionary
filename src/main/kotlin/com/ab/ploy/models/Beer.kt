package com.ab.ploy.models

import com.faunadb.client.types.FaunaConstructor
import com.faunadb.client.types.FaunaField

data class Beer @FaunaConstructor constructor(
    @FaunaField var name: String? = "default-beer-name",
    @FaunaField var alcohol: String? = "default-alcohol"
)