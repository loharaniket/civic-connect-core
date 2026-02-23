package com.civic_connect_core.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.civic_connect_core.app.dtos.issues_dtos.DistIssueResponse;
import com.civic_connect_core.app.dtos.issues_dtos.IssueRequest;
import com.civic_connect_core.app.dtos.issues_dtos.IssueResponse;
import com.civic_connect_core.app.dtos.issues_dtos.UpdateIssueStatus;
import com.civic_connect_core.app.services.DepartmentService;
import com.civic_connect_core.app.services.IssueService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/issue")
public class IssueController {
    private final IssueService service;
    private final DepartmentService departmentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> postIssue(
            @Valid @RequestPart("userData") IssueRequest request,
            @RequestPart(value = "image", required = false) MultipartFile file) {

        try {
            // 2. Check for null FIRST, then check if it's empty
            if (file == null || file.isEmpty()) {
                System.out.println("image not found");
                return ResponseEntity.badRequest().body(Map.of("Image", "Image not Found or Empty"));
            }

            // 3. (Optional but recommended) Validate it's actually an image
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body(Map.of("Image", "File must be an image"));
            }

            if (!departmentService.isDepartmentIdPresent(request.getDept_id())) {
                return ResponseEntity.badRequest().body(Map.of("Department", "Invalid Department Id"));
            }

            byte[] compressImage = service.compressImage(file);

            return ResponseEntity.ok(service.postIssue(request, compressImage));

        } catch (Exception e) {
            // This will now correctly catch your compression errors
            return ResponseEntity.badRequest()
                    .body(Map.of("Error", "Compression or Processing Error: " + e.getMessage()));
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<?> getPostImage(@PathVariable Long id) {
        byte[] image = service.getImage(id);
        if (image == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

    @GetMapping
    public List<IssueResponse> getUserIssue() {
        return service.getUserIssue();
    }

    @GetMapping("/dept")
    public List<DistIssueResponse> getDeptIssue() {
        return service.getDeptIssue();
    }

    @GetMapping("/dist")
    public List<DistIssueResponse> getDistIssue() {
        return service.getDistIssue();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateIssueStatus(@RequestBody UpdateIssueStatus request) {
        service.updateIssueStatus(request.getIssueId(), request.getStatus());
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/update/open/{id}")
    public ResponseEntity<?> udpateOpenIssue(@PathVariable Long id) {
        service.openIssueUpdate(id);
        return ResponseEntity.ok().build();
    }
    
}
