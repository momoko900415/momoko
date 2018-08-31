package com.leon.security.validator;

import com.leon.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**t
 * @Auther: chongwang
 * @Date: 2018/4/30 10:17
 */
public class MyConstraintValidator implements ConstraintValidator<MyConsraint,Object> {


    //继承了ConstraintValidator 是可以直接注入的

    @Autowired
    public HelloService helloService;


    @Override
    public void initialize(MyConsraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.gretting("leon");
        System.out.println(value);
        return false;
    }
}
