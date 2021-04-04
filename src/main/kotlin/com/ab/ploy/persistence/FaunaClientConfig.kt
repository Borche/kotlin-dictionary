/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.faunadb.client.FaunaClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FaunaClientConfig {

    @Value("\${fauna-db.secret}")
    lateinit var faunaDbSecret: String

    @Bean
    fun FaunaClient(): FaunaClient {
        return FaunaClient.builder()
            .withSecret(faunaDbSecret)
            .build()
    }
}