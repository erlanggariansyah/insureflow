package com.insureflow.product.exception.constraint.validator;

import com.insureflow.product.exception.constraint.annotation.ExistProductTypeId;
import com.insureflow.product.repository.ProductTypeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ExistProductTypeIdValidator implements ConstraintValidator<ExistProductTypeId, UUID> {
    private final ProductTypeRepository productTypeRepository;

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        if (uuid == null)
            return false;
        return productTypeRepository.existsById(uuid);
    }
}
