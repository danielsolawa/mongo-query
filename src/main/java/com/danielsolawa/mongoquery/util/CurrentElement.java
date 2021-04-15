package com.danielsolawa.mongoquery.util;

import com.danielsolawa.mongoquery.filter.MFilter;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface CurrentElement<S extends MFilter> {

    List<Criteria> apply(S s);
}
