/* Copyright © 2021 */
package com.ab.ploy.persistence

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName() = "ploy"

    override fun mongoClient(): MongoClient {
        val connectionString =
            ConnectionString(
                "mongodb+srv://andreas:DEcember10!=@ployfrankfurt.jmuac.mongodb.net/ploy?retryWrites=true&w=majority")

        val mongoClientSettings =
            MongoClientSettings.builder().applyConnectionString(connectionString).build()

        return MongoClients.create(mongoClientSettings)
    }
}
