package com.civic_connect_core.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.request.SaveUserProfileRequest;
import com.civic_connect_core.app.dtos.response.GetUserProfileResponse;
import com.civic_connect_core.app.entity.UserProfile;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.exceptions.UserNotFoundException;
import com.civic_connect_core.app.repository.UserProfileRepository;
import com.civic_connect_core.app.repository.UsersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final UsersRepository usersRepository;

    private Users getUser(Long userId) {
        return usersRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    private UserProfile getProfile(Users user) {
        return userProfileRepository.findByUsers(user).orElse(new UserProfile());
    }

    public UserProfile saveProfile(SaveUserProfileRequest request) {
        var user = getUser(request.getUserId());
        var existingProfile = getProfile(user);
        existingProfile.setFirstName(request.getFirstName());
        existingProfile.setLastName(request.getLastName());
        existingProfile.setAddress(request.getAddress());
        existingProfile.setAge(request.getAge());
        existingProfile.setUsers(user);
        userProfileRepository.save(existingProfile);
        return existingProfile;
    }

    public UserProfile getUserProfile(Long userId) {
        var user = getUser(userId);
        return getProfile(user);
    }

    public List<UserProfile> getAllUserProfiles() {
        List<UserProfile> userList = userProfileRepository.findAll();
        return userList;
    }

    // public GetUserProfileResponse getUserProfile(Long userId) {
    // var user = getUser(userId);
    // var profile = getProfile(user);
    // var response = GetUserProfileResponse.builder()
    // .profileId(profile.getProfileId())
    // .userId(userId)
    // .firstName(profile.getFirstName())
    // .lastName(profile.getLastName())
    // .age(profile.getAge())
    // .address(profile.getAddress())
    // .userEmail(user.getUserEmail())
    // .build();
    // return response;
    // }

}
