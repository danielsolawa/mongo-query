package com.danielsolawa.mongoquery.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.function.Function;

public class MCriteriaBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(MCriteriaBuilder.class.getName());

    private Query query;
    private final Map<Integer, List<Criteria>> criteriaMap = new HashMap<>();

    public static MCriteriaBuilder getInstance() {
        return new MCriteriaBuilder();
    }

    private MCriteriaBuilder() {
        this.query = new Query();
        criteriaMap.put(0, new LinkedList<>());
    }

    public MCriteriaBuilder append(Optional<Criteria> optionalCriteria) {
        optionalCriteria.ifPresent(criteria -> addToCurrentCriteria(criteria));

        return this;
    }

    public MCriteriaBuilder append(Criteria criteria) {
        if (isFilterNonNull.apply(criteria)) {
            addToCurrentCriteria(criteria);
        }

        return this;
    }

    public MCriteriaBuilder or() {
        criteriaMap.put(getLastElement() + 1, new LinkedList<>());

        return this;
    }

    @Deprecated
    public Query getQuery() {
        return query;
    }

    public Query buildQuery() {
        List<Criteria> criteriaResult = new ArrayList<>();

        criteriaMap.entrySet().forEach(entry ->
                criteriaResult.add(new Criteria().andOperator(entry.getValue().toArray(new Criteria[0])))
        );

        Criteria criteria = new Criteria().orOperator(criteriaResult.toArray(new Criteria[0]));
        query.addCriteria(criteria);

        return query;
    }


    private Function<Criteria, Boolean> isFilterNonNull = criteria ->
            criteria.getCriteriaObject().entrySet().stream()
                    .filter(entry -> entry.getKey().equals(criteria.getKey()))
                    .findFirst()
                    .map(Map.Entry::getValue)
                    .map(o -> !String.valueOf(o).contains("null"))
                    .orElse(false);

    private void addToCurrentCriteria(Criteria criteria) {
        List<Criteria> currentCriteria = getCurrentCriteria();
        currentCriteria.add(criteria);

        criteriaMap.put(getLastElement(), currentCriteria);
    }

    private List<Criteria> getCurrentCriteria() {

        return criteriaMap.get(getLastElement());
    }

    private int getLastElement() {
        return criteriaMap.size() - 1;
    }

}
