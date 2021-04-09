package com.danielsolawa.mongoquery.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MCriteriaBuilder {

    private Query query;

    public static MCriteriaBuilder getInstance(){
        return new MCriteriaBuilder();
    }

    private MCriteriaBuilder(){
        this.query = new Query();
    }

    public MCriteriaBuilder append(Criteria criteria){
        query.addCriteria(criteria);

        return this;
    }

    public Query getQuery(){
        return query;
    }



}
