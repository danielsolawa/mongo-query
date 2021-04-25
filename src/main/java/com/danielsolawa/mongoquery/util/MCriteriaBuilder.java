/*
 * Copyright (c) 2021 Daniel Solawa, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * A copy of the license is also available in the downloadable file "AL2".
 */


package com.danielsolawa.mongoquery.util;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.function.Function;

public class MCriteriaBuilder {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MCriteriaBuilder.class.getName());

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
//            LOGGER.debug("[append criteria not null]");
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
        if (getCurrentCriteria().isEmpty()){
//            LOGGER.debug("Query buildQuery is empty");
            return new Query();
        }


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
