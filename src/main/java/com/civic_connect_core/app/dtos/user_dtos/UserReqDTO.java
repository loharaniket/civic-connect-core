package com.civic_connect_core.app.dtos.user_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserReqDTO {
    @JsonProperty("name")
    private String userName;
    @JsonProperty("surname")
    private String userSurname;
    @JsonProperty("email")
    private String userEmail;
    @JsonProperty("password")
    private String password;
    @JsonProperty("dist_id")
    private Long distId;
}
