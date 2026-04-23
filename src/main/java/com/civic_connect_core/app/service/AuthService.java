package com.civic_connect_core.app.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.AuthDTO.AuthRequest;
import com.civic_connect_core.app.dtos.response.LoginResponse;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.enums.UserRoles;
import com.civic_connect_core.app.exceptions.UserAlreadyExistException;
import com.civic_connect_core.app.exceptions.UserNotFoundException;
import com.civic_connect_core.app.repository.UsersRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Users register(AuthRequest request) {
        if (usersRepository.existsByUserEmail(request.getUserEmail())) {
            throw new UserAlreadyExistException("User Already Exist");
        }
        var user = Users.builder()
                .userEmail(request.getUserEmail())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                .userRole("CITIZEN")
                .createdAt(LocalDateTime.now())
                .build();
        usersRepository.save(user);
        return user;
    }

    public LoginResponse login(AuthRequest request) {
        var user = usersRepository.findByUserEmail(request.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("Invalid Credentials"));
        if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new UserNotFoundException("Invalid Password");
        }
        var response = new LoginResponse(jwtService.generateToken(user.getUserId(), user.getUserRole()));
        return response;
    }

    @Transactional
    public Users changeUserRole(Long userId, UserRoles role) {
        var user = usersRepository.findByUserId(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setUserRole(role.name());
        return user;
    }

}
