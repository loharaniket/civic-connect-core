package com.civic_connect_core.app.dtos.user_dtos;

import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String userName;

    @NotBlank(message = "surname is required")
    @JsonProperty("surname")
    private String userSurname;

    @NotBlank(message = "email is required")
    @Email(message = "enter valid email")
    @EmailDomainValidation
    @JsonProperty("email")
    private String userEmail;
    
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 12, message = "password must be 6 character long")
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("dist_id")
    private Long distId;
}
