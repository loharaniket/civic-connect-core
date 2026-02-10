package com.civic_connect_core.app.dtos.dept_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"name","surname","email","dept_id"})
public class DeptAdminResDTO {
    @JsonProperty("name")
    private String firstName;
    @JsonProperty("surname")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("dept_id")
    private Long deptId;
}
