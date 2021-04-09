package com.danielsolawa.mongoquery.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoSpecificationBuilder {

    private Query query;

    public static MongoSpecificationBuilder getInstance(){
        return new MongoSpecificationBuilder();
    }

    private MongoSpecificationBuilder(){
        this.query = new Query();
    }

    public MongoSpecificationBuilder append(Criteria criteria){
        query.addCriteria(criteria);

        return this;
    }

    public Query getQuery(){
        return this.query;
    }



}
