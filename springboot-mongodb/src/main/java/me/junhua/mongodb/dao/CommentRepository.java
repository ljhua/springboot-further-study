package me.junhua.mongodb.dao;

import me.junhua.mongodb.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Page<Comment> findByArticleid(String articleid, Pageable pageable);
}
