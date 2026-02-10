package com.civic_connect_core.app.dtos.dept_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeptAdminReqDTO {
    @JsonProperty("name")
    private String firstName;
    @JsonProperty("surname")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("dist_admin_id")
    private Long distAdminId;
    @JsonProperty("dept_id")
    private Long deptId;
}
