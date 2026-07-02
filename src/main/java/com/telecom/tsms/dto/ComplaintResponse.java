package com.telecom.tsms.dto;

import com.telecom.tsms.enums.ComplaintStatus;
import com.telecom.tsms.enums.ComplaintType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ComplaintResponse {
    private Long id;
    private Long mobileNumberId;
    private String mobileNumber;

    private ComplaintType complaintType;
    private String description;

    private Boolean resolved;
    private ComplaintStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
    private String resolutionRemarks;
}