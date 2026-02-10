package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class DistAdminRegResDTO {
    private Long id;
    @JsonProperty("dist_name")
    private String distName;
    @JsonProperty("dist_state")
    private String distState;
    @JsonProperty("admin_name")
    private String adminName;
    @JsonProperty("admin_surname")
    private String adminSurname;
    @JsonProperty("admin_email")
    private String adminEmail;
}
