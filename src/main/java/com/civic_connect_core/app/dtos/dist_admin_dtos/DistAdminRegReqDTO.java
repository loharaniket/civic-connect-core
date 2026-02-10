package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DistAdminRegReqDTO {
    @NotBlank(message = "district name cannot be blank")
    @Size(min = 3 , message = "district name length should greater than 3")
    @JsonProperty("dist_name")
    private String distName;

    @NotBlank(message = "state is required")
    @JsonProperty("dist_state")
    private String distState;

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
    
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 12, message = "password must be 6 character long")
    @JsonProperty("admin_password")
    private String adminPassword;
}