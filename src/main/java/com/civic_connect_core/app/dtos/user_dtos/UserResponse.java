package com.civic_connect_core.app.dtos.user_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id","userName","userSurname","userEmail"})
public class UserResponse {
    private Long id;
    @JsonProperty("name")
    private String userName;
    @JsonProperty("surname")
    private String userSurname;
    @JsonProperty("email")
    private String userEmail;
}
