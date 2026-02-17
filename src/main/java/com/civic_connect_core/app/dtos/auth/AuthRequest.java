package com.civic_connect_core.app.dtos.auth;

import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "email address required")
    @Email(message = "enter valid email")
    @EmailDomainValidation
    private String email;

    @NotBlank(message = "password must be required")
    @Size(min = 6, max = 24, message = "wrong password")
    private String password;
}
