package com.openlife.product.exception.constraint.annotation;

import com.openlife.product.constant.MessageConstant;
import com.openlife.product.exception.constraint.validator.ExistPaymentFrequencyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistPaymentFrequencyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistPaymentFrequencyId {
    String message() default MessageConstant.PAYMENT_FREQUENCY_NOT_FOUND;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
