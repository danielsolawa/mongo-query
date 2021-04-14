package com.danielsolawa.mongoquery.specification;

import com.danielsolawa.mongoquery.util.MCriteria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MSpecification<T> {


    private static final String VALUE_SEPARATOR = ";";
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 100;

    private Class<T> typeClass;

    public MSpecification(Class<T> typeClass) {
        this.typeClass = typeClass;
    }


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
    public abstract MCriteria buildCriteria();

    @JsonIgnore
    public Class<?> getTypeClass(){
        return this.typeClass;
    }


    @JsonIgnore
    public Pageable getPageRequest() {
        return PageRequest.of(this.getPageNumber(), this.getPageSize(), this.getSort(this.sortBy));
    }

    public Sort getSort(List<String> sortByStringList) {
        return Optional.ofNullable(sortByStringList).map((sbs) ->
             Sort.by(this.createOrders(sbs))
        ).orElseGet(Sort::unsorted);
    }

    private List<Sort.Order> createOrders(List<String> sortByStringList) {
        return sortByStringList.stream().map(this::createOrder).collect(Collectors.toList());
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
        return Optional.ofNullable(this.pageNumber).orElse(DEFAULT_PAGE_NUMBER);
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return Optional.ofNullable(this.pageSize).orElse(DEFAULT_PAGE_SIZE);
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
