package com.telecom.tsms.dto;

import com.telecom.tsms.enums.ComplaintType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintRequest {

    @NotNull(message = "Mobile Number ID is required")
    private Long mobileNumberId;

    @NotNull(message = "complaint type is required")
    private ComplaintType complaintType;

    @NotBlank(message = "Description is required")
    @Size(max = 500)
    private String description;
}