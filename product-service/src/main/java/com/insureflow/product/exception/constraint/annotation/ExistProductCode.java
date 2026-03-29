package com.insureflow.product.exception.constraint.annotation;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.exception.constraint.validator.ExistProductCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistProductCodeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistProductCode {
    String message() default MessageConstant.PRODUCT_CODE_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
