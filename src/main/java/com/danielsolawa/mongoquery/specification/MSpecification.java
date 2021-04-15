package com.danielsolawa.mongoquery.specification;

import com.danielsolawa.mongoquery.util.MCriteria;
import com.danielsolawa.mongoquery.util.PageableSpec;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

public abstract class MSpecification<T> extends PageableSpec {

    private Class<T> typeClass;

    public MSpecification(Class<T> typeClass) {
        this.typeClass = typeClass;
    }


    @Nullable
    @JsonIgnore
    public abstract MCriteria buildCriteria();

    @JsonIgnore
    public Class<?> getTypeClass() {
        return this.typeClass;
    }


}
