package me.junhua.redis;

import me.junhua.redis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1L);
        user.setName("张无忌");
        user.setAge(10);
        user.setCreateTime(new Date());

        redisTemplate.opsForValue().set("user1", user);

        User user1 = (User) redisTemplate.opsForValue().get("user1");
        System.out.println("user1 = " + user1);
    }

}
