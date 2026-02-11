package com.civic_connect_core.app.dtos.dept_admin_dtos;

import com.civic_connect_core.app.validation.email_validation.EmailDomainValidation;
import com.civic_connect_core.app.validation.email_validation.EmailValidation;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeptAdminReqDTO {
    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String firstName;

    @NotBlank(message = "surname is required")
    @JsonProperty("surname")
    private String lastName;

    @NotBlank(message = "email is required")
    @EmailDomainValidation
    @Email(message = "email must be valid")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 6, max = 12, message = "password must be 6 character long")
    @JsonProperty("password")
    private String password;

    // @NotBlank(message = "district id is required")
    @JsonProperty("dist_admin_id")
    private Long distAdminId;

    // @NotBlank(message = "department id is required")
    @JsonProperty("dept_id")
    private Long deptId;
}
