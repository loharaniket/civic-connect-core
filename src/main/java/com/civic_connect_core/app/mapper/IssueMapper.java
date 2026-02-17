package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.dtos.issues_dtos.IssueUpdateRequest;
import com.civic_connect_core.app.entities.Issue;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    IssueResponse tResDTO(Issue request);

    void updateIssue(IssueUpdateRequest request, @MappingTarget Issue issue);
}
