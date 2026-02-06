package com.civic_connect_core.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private final UserRepository repository;
    // register new user
    @PostMapping("/register")
    public void registerUser() {
        System.out.println("register user");
    }

    // register user login
    @PostMapping("/login")
    public void loginUser() {
        System.out.println("login user");
    }

    // user update their password
    @PostMapping("/password")
    public void updatePassword() {
        System.out.println("password update");
    }

    // get user detail
    @GetMapping("/{id}")
    public String getProfile(@PathVariable Long id) {
        return String.valueOf(id);
    }

    // update user detail
    @PutMapping("/{id}")
    public void updateProfile() {
        System.out.println("update user profile");
    }

    @GetMapping()
    public void getUserDetail(){
        System.out.println(repository.findAll().toString());
    }

}
