package com.danielsolawa.mongoquery;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.example.repository.HumanRepository;
import com.danielsolawa.mongoquery.example.specification.HumanMongoSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    private final HumanRepository humanRepository;

    public Bootstrap(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Human h = new Human();
//        h.setName("ania");
//
//
//        Human h2 = new Human();
//        h.setName("tomek");
//
//
//        humanRepository.save(h2);
//        humanRepository.save(h);


        HumanMongoSpecification humanMongoSpecification  = new HumanMongoSpecification();
        humanMongoSpecification.setPageNumber(0);
        humanMongoSpecification.setPageSize(10);
        humanMongoSpecification.setSortBy(List.of("name;asc"));

        Page<Human> humans = humanRepository.findAll(humanMongoSpecification, humanMongoSpecification.getPageRequest());

        LOGGER.info("[humans {}]", humans.getContent());
    }
}
