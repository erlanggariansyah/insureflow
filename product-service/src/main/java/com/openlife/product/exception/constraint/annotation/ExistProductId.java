package com.openlife.product.exception.constraint.annotation;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.exception.constraint.validator.ExistProductIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistProductIdValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistProductId {
    String message() default MessageConstant.PRODUCT_ID_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
