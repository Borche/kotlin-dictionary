/* Copyright © 2021 */
package com.ab.ploy.common.persistence

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    private val log = LoggerFactory.getLogger(MongoConfig::class.java)

    @Value("\${mongo.db.name}") lateinit var dbName: String
    @Value("\${mongo.db.username}") lateinit var dbUsername: String
    @Value("\${mongo.db.password}") lateinit var dbPassword: String

    override fun getDatabaseName() = dbName

    override fun mongoClient(): MongoClient {

        val connectionString =
            ConnectionString(
                "mongodb+srv://$dbUsername:$dbPassword@ployfrankfurt.jmuac.mongodb.net/$dbName?retryWrites=true&w=majority")

        log.info(connectionString.toString())

        val mongoClientSettings =
            MongoClientSettings.builder().applyConnectionString(connectionString).build()

        return MongoClients.create(mongoClientSettings)
    }
}
