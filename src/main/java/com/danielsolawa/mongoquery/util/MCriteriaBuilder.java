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
    private final Map<Integer, Queue<Criteria>> criteriaMap = new HashMap<>();

    public static MCriteriaBuilder getInstance() {
        return new MCriteriaBuilder();
    }

    private MCriteriaBuilder() {
        this.query = new Query();
        criteriaMap.put(0, new LinkedList<>());
    }

    public MCriteriaBuilder append(Optional<Criteria> optionalCriteria) {
        optionalCriteria.ifPresent(criteria -> query.addCriteria(criteria));

        return this;
    }

    public MCriteriaBuilder append(Criteria criteria) {
        if (isFilterNonNull.apply(criteria)) {
//            addToCurrentCriteria(criteria);
            query.addCriteria(criteria);
        }


        return this;
    }

    public MCriteriaBuilder or() {
        criteriaMap.put(getLastElement() + 1, new LinkedList<>());

        return this;
    }


    public Query getQuery() {
        return query;
    }

    public Query buildQuery() {
        Criteria criteria = null;
        LOGGER.info("[{}]", criteriaMap.get(getLastElement()).size());

        for (Map.Entry<Integer, Queue<Criteria>> entry : criteriaMap.entrySet()) {
            final Integer key = entry.getKey();
            final Queue<Criteria> criteriaQueue = criteriaMap.get(key);

            if (key == 0) {
                LOGGER.info("[key 0]");
                while (!criteriaQueue.isEmpty()) {
                    if (criteria == null) {
                        criteria = new Criteria();
                    } else {
                        criteria.andOperator(criteriaQueue.poll());
                    }
                }
            } else {
                LOGGER.info("[key {}]", key);
                LOGGER.info("[size {}]", criteriaMap.get(key).size());
                Criteria tmp = null;
                while (!criteriaQueue.isEmpty()) {
                    if (tmp == null) {
                        tmp = new Criteria();
                    } else {
                        tmp.andOperator(criteriaQueue.poll());
                    }
                }

                if (tmp != null) {
                    LOGGER.info("adding or  operator");
                    criteria.orOperator(tmp);
                }
            }
        }

        return Objects.nonNull(criteria) ? query.addCriteria(criteria) : query;
    }


    private Function<Criteria, Boolean> isFilterNonNull = criteria ->
            criteria.getCriteriaObject().entrySet().stream()
                    .filter(entry -> entry.getKey().equals(criteria.getKey()))
                    .findFirst()
                    .map(Map.Entry::getValue)
                    .map(o -> !String.valueOf(o).contains("null"))
                    .orElse(false);

    private void addToCurrentCriteria(Criteria criteria) {
        Queue<Criteria> currentCriteria = getCurrentCriteria();
        currentCriteria.add(criteria);

        LOGGER.info("[add to current {}]", criteria == null);
        criteriaMap.put(getLastElement(), currentCriteria);
    }

    private Queue<Criteria> getCurrentCriteria() {

        return criteriaMap.get(getLastElement());
    }

    private int getLastElement() {
        return criteriaMap.size() - 1;
    }

}
