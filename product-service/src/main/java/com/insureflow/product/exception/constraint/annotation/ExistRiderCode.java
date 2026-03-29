package com.insureflow.product.exception.constraint.annotation;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.exception.constraint.validator.ExistRiderCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistRiderCodeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRiderCode {
    String message() default MessageConstant.RIDER_CODE_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
