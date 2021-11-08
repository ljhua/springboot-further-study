package me.junhua.condition.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class ClassCondition implements Condition {

    /**
     * @param context  上下文对象，用于获取环境（env：application.yml），IOC容器，classLoader对象
     * @param metadata 注解元对象，可以获取注解定义的属性值
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(ConditionOnClass.class.getName());
        String[] values = (String[]) annotationAttributes.get("value");
        try {
            for (int i = 0; i < values.length; i++) {
                Class.forName(values[i]);
            }
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
