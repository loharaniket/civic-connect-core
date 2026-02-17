package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
@JsonPropertyOrder({"id","district","state","name","surname","email"})
public class DistAdminRegResDTO {
    private Long id;
    @JsonProperty("district")
    private String distName;
    @JsonProperty("state")
    private String distState;
    @JsonProperty("name")
    private String adminName;
    @JsonProperty("surname")
    private String adminSurname;
    @JsonProperty("email")
    private String adminEmail;
}
