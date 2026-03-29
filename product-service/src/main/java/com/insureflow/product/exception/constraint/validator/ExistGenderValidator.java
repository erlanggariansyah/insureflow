package com.insureflow.product.exception.constraint.validator;

import com.insureflow.product.exception.constraint.annotation.ExistGender;
import com.insureflow.product.repository.GenderRepository;
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
