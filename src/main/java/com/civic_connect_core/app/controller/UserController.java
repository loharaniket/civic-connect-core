package com.civic_connect_core.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.request.SaveUserProfileRequest;
import com.civic_connect_core.app.dtos.response.ApiResponse;
import com.civic_connect_core.app.entity.UserProfile;
import com.civic_connect_core.app.service.UserProfileService;
import com.civic_connect_core.app.utils.SecurityContextDetails;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserProfileService userProfileService;

    private ResponseEntity<ApiResponse<UserProfile>> getUser(Long id) {
        var profile = userProfileService.getUserProfile(id);
        var response = new ApiResponse<>(true, "Profile", profile, LocalDateTime.now());
        if (profile.getProfileId() == null) {
            response.setSuccess(false);
            response.setMessage("Profile Not found");
            response.setData(null);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 1) users/me -> GET -> get profile user
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfile>> getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long id = (Long) auth.getPrincipal();
        return getUser(id);
    }

    // 2) users/me -> PATCH -> update profile
    @PostMapping("/me")
    public ResponseEntity<ApiResponse<UserProfile>> saveProfile(@RequestBody SaveUserProfileRequest request) {
        Long userId = SecurityContextDetails.getUserContextId();
        var userProfile = userProfileService.saveProfile(request, userId);
        ApiResponse<UserProfile> response = new ApiResponse<UserProfile>(true, "Profile Update Successfully",
                userProfile, LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 4) users -> GET -> get all users (Admin) sort
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUserProfiles() {
        List<UserProfile> users = userProfileService.getAllUserProfiles();
        var response = new ApiResponse<>(true, "All Users", users, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 5) users/{id} -> GET -> (Admin) get full detail of user
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long id) {
        return getUser(id);
    }

    // 6) users/{id} -> DELETE -> delete existing user account
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteUserById(@PathVariable Long id) {
        userProfileService.deleteUserById(id);
        var response = new ApiResponse<>(true, "User Delete Successfull", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
