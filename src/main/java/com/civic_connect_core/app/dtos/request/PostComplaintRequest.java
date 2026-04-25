package com.civic_connect_core.app.dtos.request;

import lombok.Data;

@Data
public class PostComplaintRequest {
    private String title;

    private String description;

    private Double latitude;

    private Double longitude;

    private String address;
}
