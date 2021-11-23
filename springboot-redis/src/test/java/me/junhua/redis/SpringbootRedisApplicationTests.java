package me.junhua.redis;

import me.junhua.redis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    void contextLoads() {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        user.setAge(10);
        user.setCreateTime(new Date());

        redisTemplate.opsForValue().set("user1", user);

        User user1 = (User) redisTemplate.opsForValue().get("user1");
        System.out.println("user1 = " + user1);
    }

    @Test
    void testHash() {
        redisTemplate.opsForHash().put("user:1", "id", 1L);
        redisTemplate.opsForHash().put("user:1", "name", "liuxuan");
        redisTemplate.opsForHash().put("user:1", "age", 33);

        String name = (String) redisTemplate.opsForHash().get("user:1", "name");
        System.out.println("name = " + name);
    }

    @Test
    void testSet() {
        redisTemplate.opsForSet().add("animalName", "lion");
        redisTemplate.opsForSet().add("animalName", "leopard ");
        redisTemplate.opsForSet().add("animalName", "panda");
        redisTemplate.opsForSet().add("animalName", "tiger");
        redisTemplate.opsForSet().add("animalName", "wolf");
        redisTemplate.opsForSet().add("animalName", "zebra");

        Set<Object> animalName = redisTemplate.opsForSet().members("animalName");
        System.out.println("animalName = " + animalName);
    }

    @Test
    void testServiceCtrl() throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.join();
    }

    private void business(Long increment) {
        System.out.println(" 业务操作执行 " + increment);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                service("初级用户2");
            }
        }
    }

    private void service(String id) {
        Long val = (Long) redisTemplate.opsForValue().get("compid:" + id);
        if (val == null) {
            // 不存在的情况
            redisTemplate.opsForValue().set("compid:" + id, Long.MAX_VALUE - 10, 20, TimeUnit.SECONDS);
        } else {
            // 存在
            try {
                Long increment = redisTemplate.opsForValue().increment("compid:" + id);
                business(Long.MAX_VALUE - increment);
            } catch (RedisSystemException e) {
                System.out.println(" 使用已经达到次数上线，请升级会员级别 ");
                return;
            }
        }
    }

}
