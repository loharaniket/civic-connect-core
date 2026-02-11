package com.civic_connect_core.app.validation.email_validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailDomainValidator implements ConstraintValidator<EmailDomainValidation, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.isBlank())
            return true;
        List<String> validEmails = new ArrayList<>(Arrays.asList("gmail.com"));
        if (validEmails.contains(value.split("@")[1])) {
            return true;
        }
        return false;
    }

}
