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

    @NotBlank(message = "Mobile number is required")
    @Size(max = 15,message = "Mobile number must not exceed 15 characters")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must contain only digits and be between 10 to 15 digits")
    private String mobileNumber;

    @NotBlank(message = "Email is requried")
    @Size(max=100,message = "Email must not exceed 100 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Size(max=50,message = "state must not exceed 50 characters")
    private String state;

    @NotNull(message = "KYC status is required")
    private KycStatus kycStatus;

}