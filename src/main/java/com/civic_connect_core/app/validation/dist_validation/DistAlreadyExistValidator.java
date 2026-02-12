package com.civic_connect_core.app.validation.dist_validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.civic_connect_core.app.repository.DistAdminRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistAlreadyExistValidator implements ConstraintValidator<DistAlreadyExistValidation, String> {
    @Autowired
    private final DistAdminRepo distAdminRepo;

    @Override
    public boolean isValid(String district, ConstraintValidatorContext context) {
        var dist = distAdminRepo.findByDistName(district);
        if (!dist.isPresent())
            return true;
        return false;
    }

}
