package com.civic_connect_core.app.validation.dist_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DistAlreadyExistValidator.class)
public @interface DistAlreadyExistValidation {
    String message() default "District Already Exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
