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

package com.danielsolawa.mongoquery;


//@Component
public class Bootstrap {

//    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

//    private final HumanRepository humanRepository;
//    private final MongoTemplate mongoTemplate;
//
//    public Bootstrap(HumanRepository humanRepository,
//                     MongoTemplate mongoTemplate) {
//        this.humanRepository = humanRepository;
//        this.mongoTemplate = mongoTemplate;
//    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        HumanMultiSpecification humanMultiSpecification = new HumanMultiSpecification();
//        humanMultiSpecification.setPageNumber(0);
//        humanMultiSpecification.setPageSize(10);
//        humanMultiSpecification.setSortBy(List.of("name;asc"));
//
//        HumanFilter hf1 = new HumanFilter();
//        hf1.setName("Adam");
//        HumanFilter hf2 = new HumanFilter();
//        hf2.setName("Daniel");
//        hf2.setDogName("acee");
//        HumanFilter hf3 = new HumanFilter();
//        hf3.setName("Tomek");
//
//
////        humanMultiSpecification.setFilterList(List.of(hf1,  hf2, hf3));
//
//        HumanMongoSpecification humanMongoSpecification  = new HumanMongoSpecification();
//        humanMongoSpecification.setPageNumber(0);
//        humanMongoSpecification.setPageSize(10);
//        humanMongoSpecification.setSortBy(List.of("name;asc"));
//        humanMongoSpecification.setName(List.of("Adam"));
//
//
////        Query query = new Query();
////        Criteria a = Criteria.where("name").in("Adam");
////        Criteria b = Criteria.where("name").in("Daniel");
////
////        Criteria criteria = new Criteria().orOperator(a,b);
////
////        query.addCriteria(criteria);
////
////        List<Human> humans = mongoTemplate.find(query, Human.class);
//
//        Page<Human> humans = humanRepository.findAll(humanMultiSpecification, humanMultiSpecification.getPageRequest());
////        Page<Human> humanssingle = humanRepository.findAll(humanMongoSpecification, humanMongoSpecification.getPageRequest());
//
//        LOGGER.info("[humans total elements {}   size {}]]", humans.getContent(), humans.getTotalElements() );
//    }
}
