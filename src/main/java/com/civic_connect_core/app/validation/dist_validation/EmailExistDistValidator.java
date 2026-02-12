package com.civic_connect_core.app.validation.dist_validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.civic_connect_core.app.repository.DistAdminRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailExistDistValidator implements ConstraintValidator<EmailExistDistValidation, String> {
    @Autowired
    private final DistAdminRepo distAdminRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isBlank())
            return true;
        var distAdmin = distAdminRepo.findByAdminEmail(value);
        if (distAdmin.isPresent()) {
            return false;
        }
        return true;

    }

}
