package com.ab.ploy.models

import com.faunadb.client.types.FaunaConstructor
import com.faunadb.client.types.FaunaField

class Beer @FaunaConstructor constructor(
    @FaunaField var name: String? = "No beer name",
    @FaunaField var alcohol: String? = "No alcohol"
)