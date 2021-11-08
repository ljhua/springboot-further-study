package me.junhua.condition.config;

import me.junhua.condition.condition.ClassCondition;
import me.junhua.condition.condition.ConditionOnClass;
import me.junhua.condition.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
//    @Conditional(ClassCondition.class)
    @ConditionOnClass("org.springframework.data.redis.core.RedisTemplate")
    public User user() {
        return new User();
    }
}
