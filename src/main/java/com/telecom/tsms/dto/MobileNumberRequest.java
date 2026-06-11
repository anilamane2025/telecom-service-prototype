package com.telecom.tsms.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MobileNumberRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10,15}$",message = "Mobile number must contain only digits and be between 10 to 15 digits")
    private String mobileNumber;

    private String status;

    private Boolean primary;

}