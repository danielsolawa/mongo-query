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

package com.danielsolawa.mongoquery.specification;

import com.danielsolawa.mongoquery.filter.MFilter;
import com.danielsolawa.mongoquery.util.CurrentElement;
import com.danielsolawa.mongoquery.util.MCriteriaBuilder;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MultiMSpecification<T, S extends MFilter> extends MSpecification<T> {


//    private final static Logger LOGGER = LoggerFactory.getLogger(MultiMSpecification.class);

    private List<S> filterList = new ArrayList<>();

    public MultiMSpecification(Class<T> typeClass) {
        super(typeClass);
    }

    public List<S> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<S> filterList) {
        this.filterList = filterList;
    }


    public MCriteriaBuilder append(MCriteriaBuilder cb, CurrentElement<S> currentElement) {
//        LOGGER.info("[filter list size {}]", filterList.size());
        for (int i = 0; i < filterList.size(); i++) {
            List<Criteria> criteriaList = currentElement.apply(getElement(i));
            if (i == 0) {
                cb = addToCriteria(cb, criteriaList);
            } else {
                cb = cb.or();
                cb = addToCriteria(cb, criteriaList);
            }
        }

        return cb;
    }


    private S getElement(int i) {
        if (Objects.isNull(this.filterList) || i >= this.filterList.size()) {
            throw new RuntimeException("Index out of bounds!");
        }

        return this.filterList.get(i);
    }

    private MCriteriaBuilder addToCriteria(MCriteriaBuilder cb, List<Criteria> criteria) {
        for (Criteria c : criteria) {
            cb = cb.append(c);
        }

        return cb;
    }

    public static List<Criteria> wrap(Criteria... criteria) {
        return List.of(criteria);
    }
}
