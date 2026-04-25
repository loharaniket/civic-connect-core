package com.civic_connect_core.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserProfileResponse {
    private Long profileId;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private Long userId;
    private String userEmail;
}
