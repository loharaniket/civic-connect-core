package com.civic_connect_core.app.dtos.dist_admin_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
@JsonPropertyOrder({ "id", "district", "state" })
public class DistrictPublicResponse {
    private Long id;
    @JsonProperty("district")
    private String distName;
    @JsonProperty("state")
    private String distState;
}
