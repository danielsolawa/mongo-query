package com.danielsolawa.mongoquery.repository;

import com.danielsolawa.mongoquery.specification.MongoSpecification;
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

public class MongoQueryRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements MongoQueryRepository<T, ID> {

    private final MongoOperations mongoOperations;

    /**
     * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
     *
     * @param metadata        must not be {@literal null}.
     * @param mongoOperations must not be {@literal null}.
     */
    public MongoQueryRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
    }


    @Override
    public List<T> findAll(MongoSpecification specification) {

       final Query query = Optional.ofNullable(specification.getQuery())
                .orElse(new Query());
       query.with(specification.getSort(specification.getSortBy()));

        return (List<T>) mongoOperations.find(query, specification.getTypeClass());
    }

    @Override
    public Page<T> findAll(MongoSpecification specification, Pageable pageable) {

        final Query query = Optional.ofNullable(specification.getQuery())
                .orElse(new Query());
        query.with(pageable);

        return new PageImpl<>((List<T>) mongoOperations.find(query, specification.getTypeClass()), pageable, count());
    }


}
