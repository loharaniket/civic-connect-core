package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DistAdminLoginReq {
    @NotBlank(message = "email is required")
    @Email(message = "enter valid email")
    @EmailDomainValidation(message = "email not found")
    private String email;

    @NotBlank(message = "wrong password")
    @Size(message = "wrong password")
    private String password;
}
