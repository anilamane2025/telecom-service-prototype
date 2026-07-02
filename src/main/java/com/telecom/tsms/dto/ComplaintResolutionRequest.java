package com.telecom.tsms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintResolutionRequest {

    @NotBlank(message = "Resolution remarks are required")
    @Size(max=500)
    private String resolutionRemarks;

}
