package com.danielsolawa.mongoquery.util;

import com.danielsolawa.mongoquery.specification.MSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MCriteriaBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MCriteriaBuilder.class.getName());

    private Query query;

    public static MCriteriaBuilder getInstance() {
        return new MCriteriaBuilder();
    }

    private MCriteriaBuilder() {
        this.query = new Query();
    }

    public  MCriteriaBuilder append(Optional<Criteria> optionalCriteria) {
        optionalCriteria.ifPresent(criteria -> query.addCriteria(criteria));

        return this;
    }

    public Query getQuery() {
        return query;
    }


}
