package com.insureflow.product.exception.constraint.validator;

import com.insureflow.product.exception.constraint.annotation.ExistRiderCode;
import com.insureflow.product.repository.RiderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistRiderCodeValidator implements ConstraintValidator<ExistRiderCode, String> {
    private final RiderRepository riderRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty())
            return false;
        return riderRepository.findByCode(s).isPresent();
    }
}
