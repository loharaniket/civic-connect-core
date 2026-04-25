package com.civic_connect_core.app.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.civic_connect_core.app.dtos.request.PostComplaintRequest;
import com.civic_connect_core.app.dtos.request.UpdateComplaintStatus;
import com.civic_connect_core.app.entity.Complaint;
import com.civic_connect_core.app.entity.Department;
import com.civic_connect_core.app.entity.Users;
import com.civic_connect_core.app.enums.ComplaintStatus;
import com.civic_connect_core.app.enums.UserRoles;
import com.civic_connect_core.app.exceptions.NotFoundException;
import com.civic_connect_core.app.exceptions.UserNotFoundException;
import com.civic_connect_core.app.repository.ComplaintRepository;
import com.civic_connect_core.app.repository.DepartmentRepository;
import com.civic_connect_core.app.repository.UsersRepository;
import com.civic_connect_core.app.utils.SecurityContextDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ComplaintService {
    private final ComplaintRepository complaintRepository;
    private final UsersRepository usersRepository;
    private final DepartmentRepository departmentRepository;
    private final String UPLOAD_PATH = new File("").getAbsolutePath() + "\\uploads\\";

    private String uploadImage(MultipartFile file) throws IOException {
        try {
            File dir = new File(UPLOAD_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String updateImgName = UPLOAD_PATH + timestamp + "_" + file.getOriginalFilename();
            File dest = new File(updateImgName);
            file.transferTo(dest);
            return updateImgName;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private List<Complaint> deptComplaints(Users user) {
        List<Complaint> complaints = new ArrayList<>();
        var dept = departmentRepository.findByUserId(user).orElse(new Department());
        if (dept != null) {
            return complaintRepository.findByDeptId(dept);
        }
        return complaints;
    }

    private List<Complaint> userComplaints(Users user) {
        return complaintRepository.findByUserId(user);
    }

    private List<Complaint> allComplaints() {
        return complaintRepository.findAll();
    }

    public void postComplaint(MultipartFile file, PostComplaintRequest request) throws IOException {
        String filePath = uploadImage(file);
        var user = usersRepository.findByUserId(SecurityContextDetails.getUserContextId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        var newPost = Complaint.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deptId(null)
                .userId(user)
                .imgUrl(filePath)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .status(ComplaintStatus.PENDING.toString())
                .address(request.getAddress())
                .build();
        complaintRepository.save(newPost);
    }

    public List<Complaint> getComplaints() {
        var user = usersRepository.findByUserId(SecurityContextDetails.getUserContextId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (user.getUserRole().equals(UserRoles.CITIZEN.name())) {
            return userComplaints(user);
        }
        if (user.getUserRole().equals(UserRoles.DEPT_ADMIN.name())) {
            return deptComplaints(user);
        }
        return allComplaints();
    }

    public void updateComplaintStatus(UpdateComplaintStatus request) {
        HashSet<String> status = new HashSet<>(
                List.of(ComplaintStatus.RESOLVE.name(), ComplaintStatus.OPEN.name(), ComplaintStatus.WORKING.name()));
        if (status.contains(request.getStatus().toUpperCase())) {
            var complaint = complaintRepository.findById(request.getComplaintId())
                    .orElseThrow(() -> new NotFoundException("Complaint Not Found"));
            complaint.setStatus(request.getStatus().toUpperCase());
            complaintRepository.save(complaint);
        } else {
            throw new NotFoundException("Status Not Found");
        }
    }

}
