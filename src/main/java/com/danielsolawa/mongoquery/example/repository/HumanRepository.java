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


package com.danielsolawa.mongoquery.example.repository;

import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.repository.MSpecificationRepository;

public interface HumanRepository extends MSpecificationRepository<Human, String> {
}
