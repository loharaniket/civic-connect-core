package com.civic_connect_core.app.dtos.dept_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeptAdminUpdateReq {
    @JsonProperty("name")
    private String firstName;
    @JsonProperty("surname")
    private String lastName;
    @JsonProperty("email")
    private String email;
}
