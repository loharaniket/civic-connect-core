package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.entities.Issue;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    IssueResponse tResDTO(Issue issue);

    Issue toIssue(IssueRequest request);
}
