package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.civic_connect_core.app.validation.dist_validation.DistAlreadyExistValidation;
import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DistAdminRequest {
    @NotBlank(message = "district name cannot be blank")
    @Size(min = 3, message = "district name length should greater than 3")
    @DistAlreadyExistValidation
    @JsonProperty("district")
    private String distName;

    @NotBlank(message = "state is required")
    @JsonProperty("state")
    private String distState;

    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String adminName;

    @NotBlank(message = "surname is required")
    @JsonProperty("surname")
    private String adminSurname;
    
    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @EmailDomainValidation
    @JsonProperty("email")
    private String adminEmail;
    
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 24, message = "password must be 6 character long")
    @JsonProperty("password")
    private String adminPassword;
}