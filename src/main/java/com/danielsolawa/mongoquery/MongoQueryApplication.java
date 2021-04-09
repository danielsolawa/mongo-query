package com.danielsolawa.mongoquery;

import com.danielsolawa.mongoquery.repository.MongoQueryRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(repositoryBaseClass = MongoQueryRepositoryImpl.class)
@SpringBootApplication
public class MongoQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoQueryApplication.class, args);
    }

}
