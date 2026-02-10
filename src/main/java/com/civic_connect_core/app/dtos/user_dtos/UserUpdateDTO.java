package com.civic_connect_core.app.dtos.user_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @NotBlank(message = "name is required")
    @JsonProperty("name")
    private String userName;

    @NotBlank(message = "surname is required")
    @JsonProperty("surname")
    private String userSurname;

    @NotBlank(message = "email is required")
    @Email(message = "enter valid email")
    @JsonProperty("email")
    private String userEmail;

    @NotBlank(message = "dist id is required")
    @JsonProperty("dist_id")
    private Long distId;
}
