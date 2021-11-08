package me.junhua.enable;

import me.junhua.other.config.MyImportBeanDefinitionRegistrar;
import me.junhua.other.domain.Role;
import me.junhua.other.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import redis.clients.jedis.Jedis;

@SpringBootApplication
@Import(MyImportBeanDefinitionRegistrar.class)
public class SpringbootEnableApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootEnableApplication.class, args);
        User user = context.getBean(User.class);
        System.out.println("user = " + user);

        Role role = context.getBean(Role.class);
        System.out.println("role = " + role);

        Jedis jedis = context.getBean(Jedis.class);
        System.out.println("jedis = " + jedis);
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

}
