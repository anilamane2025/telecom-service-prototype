package com.telecom.tsms.dto;

import com.telecom.tsms.enums.KycStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    @NotBlank(message = "Customer code is required")
    @Size(max=30, message = "Customer code must not exceed 30 characters")
    private String customerCode;

    @NotBlank(message = "Full name is required")
    @Size(message = "full name must not exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Email is requried")
    @Size(max=100,message = "Email must not exceed 100 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Size(max=50,message = "state must not exceed 50 characters")
    private String state;

    @NotNull(message = "KYC status is required")
    private KycStatus kycStatus;

}