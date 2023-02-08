package com.anserlt.common.java.util.valid;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * fixme 这个校验类不清楚原理，目前只知道怎么进行使用
 *
 * 需要使用的时候，要在使用参数校验的类上添加注解：
 *          @Validated
 * 在方法中直接进行调用使用就可以了，如：
 *          ValidatorUtil.validate(Object);
 *
 * @author Anserlt
 */
@Slf4j
public class ValidUtil {

    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory().getValidator();

    /**
     * 实体校验
     */
    public static <T> Boolean validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, new Class[0]);
        if (constraintViolations.size() > 0) {
            ConstraintViolation<T> validateInfo = (ConstraintViolation<T>) constraintViolations.iterator().next();
            log.error(validateInfo.getMessage());
            return false;
        }
        return true;
    }

}
