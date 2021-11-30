package me.junhua.mongodb;

import me.junhua.mongodb.dao.CommentRepository;
import me.junhua.mongodb.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootMongodbApplicationTests {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void testFindAll() {
        List<Comment> commentList = commentRepository.findAll();
        commentList.forEach(x -> System.out.println("x = " + x));
    }

    @Test
    void testFindById() {
        Comment comment = commentRepository.findById("1").get();
        System.out.println("comment = " + comment);
    }

    @Test
    void testInsert() {
        Comment comment = new Comment();
        comment.setContent("测试insert");
        comment.setPublishtime(new Date());
        comment.setUserid("liujunhua");
        comment.setNickname("刘君华");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setLikenum(0);
        comment.setReplynum(0);
        comment.setState("1");
        comment.setParentid(null);
        comment.setArticleid(null);
        commentRepository.insert(comment);
    }

    @Test
    void testFindByParentid() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Comment> commentPage = commentRepository.findByArticleid("100001", pageable);
        List<Comment> commentList = commentPage.getContent();
        commentList.forEach(x -> System.out.println("x = " + x));
    }

}
