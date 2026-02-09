package com.civic_connect_core.app.dtos.issues_dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class IssueReqDTO {
    private String title;
    @JsonProperty("desc")
    private String description;
    @JsonProperty("auther_id")
    private Long autherId;
}
