package com.openlife.product.exception.constraint.validator;

import com.openlife.product.exception.constraint.annotation.ExistGender;
import com.openlife.product.repository.GenderRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistGenderValidator implements ConstraintValidator<ExistGender, Integer> {
    private final GenderRepository genderRepository;

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null || integer == 0)
            return true;
        return genderRepository.existsById(integer);
    }
}
