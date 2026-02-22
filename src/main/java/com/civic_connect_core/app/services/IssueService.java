package com.civic_connect_core.app.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.entities.Issue;
import com.civic_connect_core.app.mapper.IssueMapper;
import com.civic_connect_core.app.repository.IssueRepo;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@Service
@AllArgsConstructor
public class IssueService {
    private final IssueRepo repo;
    private final IssueMapper mapper;
    private final UsersService usersService;
    private final DeptAdminService deptAdminService;
    private final DistrictAdminService districtAdminService;
    private final DepartmentService deptService;

    // public IssueResponse postIssue(IssueRequest request) {
    // var user = usersService.getUserDetail();
    // Issue issue = Issue.builder()
    // .title(request.getTitle())
    // .description(request.getDescription())
    // .autherId(user.getId())
    // .distId(user.getDistId())
    // .deptId(request.getDept_id())
    // .status("PENDING")
    // .createdAt(LocalDateTime.now())
    // .build();
    // // repo.save(issue);
    // return mapper.tResDTO(issue);
    // }

    public IssueResponse postIssue(IssueRequest request, byte[] image) {
        var user = usersService.getUserDetail();
        Issue issue = Issue.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .autherId(user.getId())
                .distId(user.getDistId())
                .deptId(request.getDept_id())
                .status("PENDING")
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .image(image)
                .createdAt(LocalDateTime.now())
                .build();
        repo.save(issue);

        return getIssueResponse(issue);
    }

    private IssueResponse getIssueResponse(Issue issue) {
        return IssueResponse.builder()
                .id(issue.getId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .status(issue.getStatus())
                .latitude(issue.getLatitude())
                .longitude(issue.getLongitude())
                .imageLocation("http://localhost:8080/api/issue/image/" + issue.getId())
                .department(deptService.getDeptName(issue.getDeptId()))
                .createdAt(issue.getCreatedAt())
                .build();
    }

    public List<IssueResponse> getUserIssue() {
        var user = usersService.getUserDetail();
        return repo.findByAutherId(user.getId()).stream().map(issue -> getIssueResponse(issue)).toList();
    }

    public List<IssueResponse> getDeptIssue() {
        var deptAdmin = deptAdminService.getDepartmentAdminDetail();
        return repo.findByDistId(deptAdmin.getDistAdminId()).stream()
                .filter(issue -> issue.getDeptId() == deptAdmin.getDeptId())
                .map(issue -> getIssueResponse(issue)).toList();
    }

    public List<IssueResponse> getDistIssue() {
        var distAdmin = districtAdminService.getContextDistAdmin();
        return repo.findByDistId(distAdmin.getId()).stream().map(issue -> getIssueResponse(issue)).toList();
    }

    public byte[] compressImage(MultipartFile file) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(file.getInputStream())
                .size(800, 800)
                .outputQuality(0.2)
                .outputFormat("jpg")
                .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }

    public byte[] getImage(Long id) {
        var issue = repo.findById(id).orElseThrow();
        return issue.getImage();
    }

}
