package me.junhua.mongodb;

import me.junhua.mongodb.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@SpringBootTest
public class MyTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        Query query = new Query(Criteria.where("_id").is("1"));
        Update update = new Update();
        update.inc("likenum");
        mongoTemplate.updateFirst(query, update, Comment.class);
    }
}
