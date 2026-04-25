package com.civic_connect_core.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.request.SaveUserProfileRequest;
import com.civic_connect_core.app.entity.UserProfile;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.exceptions.UserNotFoundException;
import com.civic_connect_core.app.repository.UserProfileRepository;
import com.civic_connect_core.app.repository.UsersRepository;
import com.civic_connect_core.app.utils.SecurityContextDetails;

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

    public UserProfile saveProfile(SaveUserProfileRequest request, Long userId) {
        var user = getUser(userId);
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
        for (UserProfile user : userList) {
            if (user.getUsers().getUserId() == SecurityContextDetails.getUserContextId()) {
                userList.remove(user);
            }
        }
        return userList;
    }

    public void deleteUserById(Long id) {
        var user = getUser(id);
        usersRepository.delete(user);
    }

}
