package com.leon.security.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: chongwang
 * @Date: 2018/4/30 10:13
 * 自定义注解
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConsraint {

    String message() ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
