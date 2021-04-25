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
