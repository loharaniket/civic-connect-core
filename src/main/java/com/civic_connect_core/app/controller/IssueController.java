package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.services.DepartmentService;
import com.civic_connect_core.app.services.IssueService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/issue")
public class IssueController {
    private final IssueService service;
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> postIssue(@RequestBody IssueRequest request) {
        System.out.println(request.getDept_id());
        if (!departmentService.isDepartmentIdPresent(request.getDept_id())) {
            return ResponseEntity.badRequest().body(Map.of("Department", "Invalid Department Id"));
        }
        return ResponseEntity.ok(service.postIssue(request));
    }

    @GetMapping
    public List<IssueResponse> getUserIssue() {
        return service.getUserIssue();
    }

    @GetMapping("/dept")
    public List<IssueResponse> getDeptIssue() {
        return service.getDeptIssue();
    }

    @GetMapping("/dist")
    public List<IssueResponse> getDistIssue() {
        return service.getDistIssue();
    }
}
