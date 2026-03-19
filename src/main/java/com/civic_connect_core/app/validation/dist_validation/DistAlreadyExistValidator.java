package com.civic_connect_core.app.validation.dist_validation;

import com.civic_connect_core.app.repository.DistAdminRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistAlreadyExistValidator implements ConstraintValidator<DistAlreadyExistValidation, String> {
    private final DistAdminRepo distAdminRepo;

    @Override
    public boolean isValid(String district, ConstraintValidatorContext context) {
        return distAdminRepo.existsByDistName(district);
    }

}
