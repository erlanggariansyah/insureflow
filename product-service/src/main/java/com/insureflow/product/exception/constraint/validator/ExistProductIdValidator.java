package com.insureflow.product.exception.constraint.validator;

import com.insureflow.product.exception.constraint.annotation.ExistProductId;
import com.insureflow.product.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ExistProductIdValidator implements ConstraintValidator<ExistProductId, UUID> {
    private final ProductRepository productRepository;

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        if (uuid == null)
            return false;
        return productRepository.existsById(uuid);
    }
}
