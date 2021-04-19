package com.danielsolawa.mongoquery.example.specification;

import com.danielsolawa.mongoquery.example.filter.HumanFilter;
import com.danielsolawa.mongoquery.example.model.Human;
import com.danielsolawa.mongoquery.specification.MultiMSpecification;
import com.danielsolawa.mongoquery.util.MCriteria;
import org.springframework.data.mongodb.core.query.Criteria;

public class HumanMultiSpecification extends MultiMSpecification<Human, HumanFilter> {

//    private final static Logger LOGGER = LoggerFactory.getLogger(HumanMultiSpecification.class);

    public HumanMultiSpecification() {
        super(Human.class);
    }

    @Override
    public MCriteria buildCriteria() {
        return cb -> append(cb, humanFilter ->
                wrap(
                        Criteria.where("name").in(humanFilter.getName()),
                        Criteria.where("dogList.name").in(humanFilter.getDogName())
                ));

    }

}
