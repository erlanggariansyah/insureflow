package com.openlife.product.exception.constraint.validator;

import com.openlife.product.exception.constraint.annotation.ExistPaymentFrequencyId;
import com.openlife.product.repository.PaymentFrequencyRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistPaymentFrequencyValidator implements ConstraintValidator<ExistPaymentFrequencyId, Integer> {
    private final PaymentFrequencyRepository paymentFrequencyRepository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null || integer == 0)
            return false;
        return paymentFrequencyRepository.existsById(integer);
    }
}
