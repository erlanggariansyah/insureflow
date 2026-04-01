package com.openlife.product.exception.constraint.validator;

import com.openlife.product.exception.constraint.annotation.ExistProductCode;
import com.openlife.product.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistProductCodeValidator implements ConstraintValidator<ExistProductCode, String> {
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty())
            return false;
        return productRepository.findByCode(s).isPresent();
    }
}
