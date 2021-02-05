package org.casey.basic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationTypeName MyAnnotation
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 自定义注解, 成员只能是基本类型或String,
 * @Date 2020/9/6
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface MyAnnotation {

    String username() default "casey";

    int age() default 21;
}
