package com.civic_connect_core.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civic_connect_core.app.dtos.issues_dtos.IssueReqDTO;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResDTO;
import com.civic_connect_core.app.dtos.issues_dtos.IssueUpdateDTO;
import com.civic_connect_core.app.entities.Issue;
import com.civic_connect_core.app.mapper.IssueCustomMapping;
import com.civic_connect_core.app.mapper.IssueMapper;
import com.civic_connect_core.app.repository.IssueRepo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/issue")
public class IssueController {
    private final IssueMapper issueMapper;
    @Autowired
    private final IssueCustomMapping customMapping;

    @Autowired
    private final IssueRepo issueRepo;

    @GetMapping
    public List<IssueResDTO> getAllIssue() {
        return issueRepo.findAll().stream().map(issue -> issueMapper.tResDTO(issue)).toList();
    }

    @PostMapping
    public ResponseEntity<IssueResDTO> postIssue(@RequestBody IssueReqDTO request) {
        if (request == null)
            return ResponseEntity.badRequest().build();
        var req = customMapping.reqToIssue(request);
        issueRepo.save(req);
        var res = issueMapper.tResDTO(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IssueResDTO> updateIssue(@RequestBody IssueUpdateDTO request, @PathVariable Long id) {
        var issue = issueRepo.findById(id).orElseThrow(() -> new RuntimeException());
        issueMapper.updateIssue(request, issue);
        issueRepo.save(issue);
        return ResponseEntity.ok(issueMapper.tResDTO(issue));
    }
}
