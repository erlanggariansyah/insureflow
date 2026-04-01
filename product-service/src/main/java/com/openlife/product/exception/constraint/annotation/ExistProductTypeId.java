package com.openlife.product.exception.constraint.annotation;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.exception.constraint.validator.ExistProductTypeIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistProductTypeIdValidator.class)
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistProductTypeId {
    String message() default MessageConstant.PRODUCT_TYPE_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
