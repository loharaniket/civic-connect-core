package com.civic_connect_core.app.dtos.dept_admin_dtos;

import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeptAdminUpdateReq {

    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String firstName;

    @NotBlank(message = "surname is required")
    @JsonProperty("surname")
    private String lastName;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @EmailDomainValidation
    @JsonProperty("email")
    private String email;
}
