package com.civic_connect_core.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.civic_connect_core.app.dtos.request.PostComplaintRequest;
import com.civic_connect_core.app.dtos.request.UpdateComplaintStatus;
import com.civic_connect_core.app.dtos.response.ApiResponse;
import com.civic_connect_core.app.entity.Complaint;
import com.civic_connect_core.app.service.ComplaintService;

import lombok.AllArgsConstructor;

@RequestMapping("api/v1/complaints")
@RestController
@AllArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> imgUploadTest(@RequestPart("file") MultipartFile file,
            @RequestPart("data") PostComplaintRequest request) {
        var response = new ApiResponse<>(false, null, null, null);
        if (file.isEmpty()) {
            response.setMessage("Img Not Found!");
            return ResponseEntity.badRequest().body(response);
        }
        try {
            complaintService.postComplaint(file, request);
        } catch (IOException e) {
            response.setMessage("Image Not Save!");
            return ResponseEntity.badRequest().body(response);
        }
        response.setSuccess(true);
        response.setMessage("Post Submitted Successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Complaint>>> getComplaints() {
        List<Complaint> complaints = complaintService.getComplaints();
        System.out.println(complaints.toString());
        var response = new ApiResponse<>(true, "Success", complaints, LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Object>> updateComplaintStatus(@RequestBody UpdateComplaintStatus request) {
        complaintService.updateComplaintStatus(request);
        var response = new ApiResponse<>(true, "Update Successfully", null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
