/* Copyright © 2021 */
package com.ab.ploy.models.fauna

import com.faunadb.client.types.FaunaConstructor
import com.faunadb.client.types.FaunaField

data class FaunaBeer
@FaunaConstructor
constructor(
    @FaunaField var id: String?,
    @FaunaField var name: String? = "default-beer-name",
    @FaunaField var alcohol: String? = "default-alcohol"
)
