package com.danielsolawa.mongoquery.example.repository;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.repository.MongoQueryRepository;

public interface HumanRepository extends MongoQueryRepository<Human, String> {
}
