package com.civic_connect_core.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.civic_connect_core.app.dtos.issues_dtos.IssueReqDTO;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResDTO;
import com.civic_connect_core.app.dtos.issues_dtos.IssueUpdateDTO;
import com.civic_connect_core.app.entities.Issue;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    IssueResDTO tResDTO(Issue request);

    void updateIssue(IssueUpdateDTO request, @MappingTarget Issue issue);
}
