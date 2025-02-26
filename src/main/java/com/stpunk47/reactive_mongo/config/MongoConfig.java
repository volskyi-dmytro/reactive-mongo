package com.stpunk47.reactive_mongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

import static java.util.Collections.singletonList;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${MONGO_HOST}")
    private String mongoHost;

    @Override
    protected String getDatabaseName() {
        return "spring_test";
    }

    @Override
    protected void configureClientSettings(MongoClientSettings.Builder builder) {
        builder.credential(MongoCredential.createCredential("root", "admin",
                        "example".toCharArray()))
                .applyToClusterSettings(settings -> {
                    settings.hosts(singletonList(
                            new ServerAddress(mongoHost, 27017)
                    ));
                });
    }
}
