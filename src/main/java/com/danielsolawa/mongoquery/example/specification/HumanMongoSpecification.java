package com.danielsolawa.mongoquery.example.specification;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.specification.MSpecification;
import com.danielsolawa.mongoquery.util.MCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;
import java.util.Optional;

public class HumanMongoSpecification extends MSpecification<Human> {

    private List<String> name = null;
    private List<String> dogName = List.of("ace");


    public HumanMongoSpecification() {
        super(Human.class);
    }

    @Override
    public MCriteria buildCriteria() {
        return cb -> cb
                .append(Optional.ofNullable(name).map(f -> Criteria.where("name").in(f)))
                .append(Optional.ofNullable(dogName).map(f -> Criteria.where("dogList.name").in(f)));
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
