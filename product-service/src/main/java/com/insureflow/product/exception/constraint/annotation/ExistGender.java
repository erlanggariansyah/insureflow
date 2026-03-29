package com.insureflow.product.exception.constraint.annotation;

import com.insureflow.product.constant.MessageConstant;
import com.insureflow.product.exception.constraint.validator.ExistGenderValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistGenderValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistGender {
    String message() default MessageConstant.GENDER_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
