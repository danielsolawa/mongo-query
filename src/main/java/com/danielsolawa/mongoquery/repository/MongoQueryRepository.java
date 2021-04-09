package com.danielsolawa.mongoquery.repository;


import com.danielsolawa.mongoquery.specification.MongoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MongoQueryRepository <T, ID> extends MongoRepository<T, ID> {

    List<T> findAll(MongoSpecification specification);

    Page<T> findAll(MongoSpecification specification, Pageable pageable);
}
