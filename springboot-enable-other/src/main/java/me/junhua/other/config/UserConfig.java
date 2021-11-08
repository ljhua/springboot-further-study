package me.junhua.other.config;

import me.junhua.other.domain.Role;
import me.junhua.other.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setName("用户姓名");
        return user;
    }

    @Bean
    public Role role() {
        Role role = new Role();
        role.setName("角色名称");
        return role;
    }
}
