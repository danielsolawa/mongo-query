package com.danielsolawa.mongoquery;

import com.danielsolawa.mongoquery.example.filter.HumanFilter;
import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.example.repository.HumanRepository;
import com.danielsolawa.mongoquery.example.specification.HumanMongoSpecification;
import com.danielsolawa.mongoquery.example.specification.HumanMultiSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class Bootstrap {

//    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

//    private final HumanRepository humanRepository;
//    private final MongoTemplate mongoTemplate;
//
//    public Bootstrap(HumanRepository humanRepository,
//                     MongoTemplate mongoTemplate) {
//        this.humanRepository = humanRepository;
//        this.mongoTemplate = mongoTemplate;
//    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        HumanMultiSpecification humanMultiSpecification = new HumanMultiSpecification();
//        humanMultiSpecification.setPageNumber(0);
//        humanMultiSpecification.setPageSize(10);
//        humanMultiSpecification.setSortBy(List.of("name;asc"));
//
//        HumanFilter hf1 = new HumanFilter();
//        hf1.setName("Adam");
//        HumanFilter hf2 = new HumanFilter();
//        hf2.setName("Daniel");
//        hf2.setDogName("acee");
//        HumanFilter hf3 = new HumanFilter();
//        hf3.setName("Tomek");
//
//
////        humanMultiSpecification.setFilterList(List.of(hf1,  hf2, hf3));
//
//        HumanMongoSpecification humanMongoSpecification  = new HumanMongoSpecification();
//        humanMongoSpecification.setPageNumber(0);
//        humanMongoSpecification.setPageSize(10);
//        humanMongoSpecification.setSortBy(List.of("name;asc"));
//        humanMongoSpecification.setName(List.of("Adam"));
//
//
////        Query query = new Query();
////        Criteria a = Criteria.where("name").in("Adam");
////        Criteria b = Criteria.where("name").in("Daniel");
////
////        Criteria criteria = new Criteria().orOperator(a,b);
////
////        query.addCriteria(criteria);
////
////        List<Human> humans = mongoTemplate.find(query, Human.class);
//
//        Page<Human> humans = humanRepository.findAll(humanMultiSpecification, humanMultiSpecification.getPageRequest());
////        Page<Human> humanssingle = humanRepository.findAll(humanMongoSpecification, humanMongoSpecification.getPageRequest());
//
//        LOGGER.info("[humans total elements {}   size {}]]", humans.getContent(), humans.getTotalElements() );
//    }
}
