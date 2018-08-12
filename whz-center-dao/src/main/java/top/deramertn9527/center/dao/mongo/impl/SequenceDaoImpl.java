package top.deramertn9527.center.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import top.deramertn9527.center.dao.mongo.SequenceDao;
import top.deramertn9527.center.domain.mongo.MongoSequence;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class SequenceDaoImpl implements SequenceDao {

    @Autowired
    MongoTemplate mongo;

    public Long get(String collectionName) {
        MongoSequence seq = mongo.findAndModify(
                query(where("_id").is(collectionName)),
                new Update().inc("seq", 1),
                options().upsert(true).returnNew(true),
                MongoSequence.class);

        return seq.getSeq();
    }

}