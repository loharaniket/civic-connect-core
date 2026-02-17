package com.civic_connect_core.app.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/issue")
public class IssueController {
    // @PostMapping
    // public ResponseEntity<?> postIssue(@RequestBody IssueRequest request) {
        
    // }
}
