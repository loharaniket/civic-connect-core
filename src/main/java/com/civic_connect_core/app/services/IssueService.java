package com.civic_connect_core.app.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.entities.Issue;
import com.civic_connect_core.app.mapper.IssueMapper;
import com.civic_connect_core.app.repository.IssueRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IssueService {
    private final IssueRepo repo;
    private final IssueMapper mapper;
    private final UsersService usersService;

    public IssueResponse postIssue(IssueRequest request) {
        var user = usersService.getUserDetail();
        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .autherId(user.getId())
                .distId(user.getDistId())
                .deptId(request.getDept_id())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
        repo.save(issue);
        return mapper.tResDTO(issue);
    }

}
