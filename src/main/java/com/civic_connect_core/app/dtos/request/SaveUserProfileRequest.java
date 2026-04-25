package com.civic_connect_core.app.dtos.request;
import lombok.Data;

@Data
public class SaveUserProfileRequest {
    private String firstName;

    private String lastName;

    private int age;

    private String address;

}
