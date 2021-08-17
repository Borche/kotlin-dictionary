/* Copyright © 2021 */
package com.ab.ploy.persistence;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  protected String getDatabaseName() {
    return "ploy";
  }

  public MongoClient mongoClient() {
    ConnectionString connectionString =
        new ConnectionString(
            "mongodb+srv://andreas:hej123HEJ@ployfrankfurt.jmuac.mongodb.net/ploy?retryWrites=true&w=majority");

    MongoClientSettings mongoClientSettings =
        MongoClientSettings.builder().applyConnectionString(connectionString).build();

    return MongoClients.create(mongoClientSettings);
  }
}
