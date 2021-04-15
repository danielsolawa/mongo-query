package com.danielsolawa.mongoquery;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.example.repository.HumanRepository;
import com.danielsolawa.mongoquery.example.specification.HumanMongoSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    private final HumanRepository humanRepository;
    private final MongoTemplate mongoTemplate;

    public Bootstrap(HumanRepository humanRepository,
                     MongoTemplate mongoTemplate) {
        this.humanRepository = humanRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
//        Human h = new Human();
//        h.setName("Tomek");
//
//        humanRepository.save(h);
//
//
//        Human h2 = new Human();
//        h.setName("tomek");
//
//
//        humanRepository.save(h2);
//


        HumanMongoSpecification humanMongoSpecification  = new HumanMongoSpecification();
        humanMongoSpecification.setPageNumber(0);
        humanMongoSpecification.setPageSize(10);
        humanMongoSpecification.setSortBy(List.of("name;asc"));

//        Query query = new Query();
//        Criteria a = Criteria.where("name").in("Adam");
//        Criteria b = Criteria.where("name").in("Daniel");
//
//        Criteria criteria = new Criteria().orOperator(a,b);
//
//        query.addCriteria(criteria);
//
//        List<Human> humans = mongoTemplate.find(query, Human.class);

        Page<Human> humans = humanRepository.findAll(humanMongoSpecification, humanMongoSpecification.getPageRequest());

        LOGGER.info("[humans {}]", humans.getContent());
    }
}
