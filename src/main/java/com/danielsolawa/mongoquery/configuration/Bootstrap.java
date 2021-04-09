package com.danielsolawa.mongoquery.configuration;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.example.repository.HumanRepository;
import com.danielsolawa.mongoquery.example.specification.HumanMongoSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;
    private final HumanRepository humanRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class.getName());

    public Bootstrap(MongoTemplate mongoTemplate, HumanRepository humanRepository) {
        this.mongoTemplate = mongoTemplate;
        this.humanRepository = humanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Human human = new Human();
//        human.setName("Adam");
//
//
//        humanRepository.save(human);


        HumanMongoSpecification humanMongoSpecification = new HumanMongoSpecification();
        humanMongoSpecification.setPageNumber(0);
        humanMongoSpecification.setPageSize(1);
//        humanMongoSpecification.setSortBy(Collections.singletonList("name;desc"));
//        Page<Human> all = humanRepository.findAll(humanMongoSpecification, humanMongoSpecification.getPageRequest());

        List<Human> humanList = humanRepository.findAll(humanMongoSpecification);

        LOGGER.info("[run] {}", humanList);
    }



}
