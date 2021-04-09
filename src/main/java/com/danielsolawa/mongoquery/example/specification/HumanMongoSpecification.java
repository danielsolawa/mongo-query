package com.danielsolawa.mongoquery.example.specification;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.specification.MSpecification;
import com.danielsolawa.mongoquery.util.MCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

public class HumanMongoSpecification extends MSpecification<Human> {

    public HumanMongoSpecification() {
        super(Human.class);
    }

    @Override
    public MCriteria buildCriteria() {
        return cb -> cb
                .append(Criteria.where("name").in("tomek", "Daniel"))
                .append(Criteria.where("dogList.name").in("ace"));
    }


}
