package com.danielsolawa.mongoquery.example.specification;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.specification.MongoSpecification;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class HumanMongoSpecification extends MongoSpecification {

    @Override
    public Query getQuery() {
        return new Query().addCriteria(Criteria.where("name").in("Daniel", "Adam"));
    }

    @Override
    public Class<?> getTypeClass() {
        return Human.class;
    }
}
