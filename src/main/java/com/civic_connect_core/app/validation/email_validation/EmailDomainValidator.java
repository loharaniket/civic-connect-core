package com.civic_connect_core.app.validation.email_validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomainValidation, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (!email.isBlank())
            return true;
        List<String> validEmails = new ArrayList<>(
                Arrays.asList("gmail.com", "outlook.com", "yahoo.com", "icloud.com"));
        for (String domain : validEmails) {
            if (email.endsWith(domain))
                return true;
        }
        return false;
    }

}
