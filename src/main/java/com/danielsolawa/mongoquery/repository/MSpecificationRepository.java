package com.danielsolawa.mongoquery.repository;


import com.danielsolawa.mongoquery.specification.MSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface MSpecificationRepository<T, ID> extends MongoRepository<T, ID> {

    List<T> findAll(MSpecification specification);

    Page<T> findAll(MSpecification specification, Pageable pageable);
}
