package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DistAdminUpdateReqDTO {
    @NotBlank(message = "name is required")
    @JsonProperty("admin_name")
    private String adminName;

    @NotBlank(message = "surname is required")
    @JsonProperty("admin_surname")
    private String adminSurname;

    @NotBlank(message = "email is required")
    @Email(message = "email must be valid")
    @JsonProperty("admin_email")
    private String adminEmail;
}
