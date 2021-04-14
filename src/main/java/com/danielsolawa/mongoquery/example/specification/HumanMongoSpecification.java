package com.danielsolawa.mongoquery.example.specification;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.specification.MSpecification;
import com.danielsolawa.mongoquery.util.MCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class HumanMongoSpecification extends MSpecification<Human> {

    private List<String> name = List.of("Adam", "Daniel");
    private List<String> dogName = List.of("ramzes", "ace");


    public HumanMongoSpecification() {
        super(Human.class);
    }

    @Override
    public MCriteria buildCriteria() {
        return cb -> cb
                .append(Criteria.where("name").in(name));
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
