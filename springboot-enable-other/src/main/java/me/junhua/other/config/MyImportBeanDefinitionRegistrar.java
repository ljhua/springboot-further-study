package me.junhua.other.config;

import me.junhua.other.domain.Role;
import me.junhua.other.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ImportBeanDefinitionRegistrar.super.registerBeanDefinitions(importingClassMetadata, registry);
        registry.registerBeanDefinition("user", BeanDefinitionBuilder.rootBeanDefinition(User.class).getBeanDefinition());
        registry.registerBeanDefinition("role", BeanDefinitionBuilder.rootBeanDefinition(Role.class).getBeanDefinition());
    }
}
