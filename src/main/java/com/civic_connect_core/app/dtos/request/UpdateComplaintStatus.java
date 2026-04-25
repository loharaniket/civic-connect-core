package com.civic_connect_core.app.dtos.request;

import lombok.Data;

@Data
public class UpdateComplaintStatus {
    private Long complaintId;
    private String status;

}
