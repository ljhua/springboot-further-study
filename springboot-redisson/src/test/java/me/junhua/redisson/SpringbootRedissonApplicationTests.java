package me.junhua.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    int i = 0;

    @Test
    void contextLoads() throws InterruptedException {
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(() -> {
                RLock lock = redissonClient.getLock("name:hhhjjj");
                lock.lock();
                System.out.println("i = " + i);
                i++;
                lock.unlock();
            });
            thread.start();
            thread.join();
        }
    }

}
