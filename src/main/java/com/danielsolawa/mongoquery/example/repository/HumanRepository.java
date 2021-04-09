package com.danielsolawa.mongoquery.example.repository;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.repository.MSpecificationRepository;

public interface HumanRepository extends MSpecificationRepository<Human, String> {
}
