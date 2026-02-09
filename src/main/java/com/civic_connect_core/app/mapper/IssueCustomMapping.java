package com.civic_connect_core.app.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.civic_connect_core.app.dtos.issues_dtos.IssueReqDTO;
import com.civic_connect_core.app.entities.Issue;

@Component
public class IssueCustomMapping {
    public Issue reqToIssue(IssueReqDTO request) {
        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .autherId(request.getAutherId())
                .status("pending")
                .deptId((long) 1)
                .distId((long) 1)
                .createdAt(LocalDateTime.now())
                .build();
        return issue;
    }
}
