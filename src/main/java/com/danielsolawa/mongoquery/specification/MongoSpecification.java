package com.danielsolawa.mongoquery.specification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MongoSpecification {


    private static final String VALUE_SEPARATOR = ";";
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 100;
    @Min(0L)
    @Nullable
    private Integer pageNumber;
    @Min(1L)
    @Nullable
    private Integer pageSize = 100;
    @Nullable
    private List<String> sortBy;

    @Nullable
    @JsonIgnore
    public abstract Query getQuery();

    @JsonIgnore
    public abstract Class<?> getTypeClass();

    public MongoSpecification() {
    }

    @JsonIgnore
    public Pageable getPageRequest() {
        return PageRequest.of(this.getPageNumber(), this.getPageSize(), this.getSort(this.sortBy));
    }

    public Sort getSort(List<String> sortByStringList) {
        return (Sort) Optional.ofNullable(sortByStringList).map((sbs) -> {
            return Sort.by(this.createOrders(sbs));
        }).orElseGet(Sort::unsorted);
    }

    private List<Sort.Order> createOrders(List<String> sortByStringList) {
        return (List)sortByStringList.stream().map(this::createOrder).collect(Collectors.toList());
    }

    private Sort.Order createOrder(String sortByString) {
        String[] sortByParts = sortByString.split(VALUE_SEPARATOR);
        String property = sortByParts[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortByParts.length > 1) {
            direction = Sort.Direction.fromString(sortByParts[1]);
        }

        return new Sort.Order(direction, property);
    }

    public Integer getPageNumber() {
        return (Integer)Optional.ofNullable(this.pageNumber).orElse(DEFAULT_PAGE_NUMBER);
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return (Integer)Optional.ofNullable(this.pageSize).orElse(DEFAULT_PAGE_SIZE);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getSortBy() {
        return this.sortBy;
    }

    public void setSortBy(List<String> sortBy) {
        this.sortBy = sortBy;
    }

}
