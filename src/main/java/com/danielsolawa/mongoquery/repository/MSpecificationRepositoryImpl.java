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

package com.danielsolawa.mongoquery.repository;

import com.danielsolawa.mongoquery.specification.MSpecification;
import com.danielsolawa.mongoquery.util.MCriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;


import java.util.List;
import java.util.Optional;

public class MSpecificationRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements MSpecificationRepository<T, ID> {

    private final MongoOperations mongoOperations;

    /**
     * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
     *
     * @param metadata        must not be {@literal null}.
     * @param mongoOperations must not be {@literal null}.
     */
    public MSpecificationRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }


    @Override
    public List<T> findAll(MSpecification specification) {
        final Query query = getQuery(specification);
        query.with(specification.getSort(specification.getSortBy()));

        return (List<T>) mongoOperations.find(query, specification.getTypeClass());
    }

    @Override
    public Page<T> findAll(MSpecification specification, Pageable pageable) {
        final Query query = getQuery(specification);
        long count = mongoOperations.count(query, specification.getTypeClass());
        query.with(pageable);

        return new PageImpl<>(
                (List<T>) mongoOperations.find(query, specification.getTypeClass()),
                pageable,
                count
        );

    }


    private Query getQuery(MSpecification specification) {
        return Optional.ofNullable(specification.buildCriteria().execute(MCriteriaBuilder.getInstance()))
                .map(MCriteriaBuilder::buildQuery)
                .orElse(new Query());
    }


}
