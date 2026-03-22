package com.telecom.tsms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {

    private Long id;
    private String customerCode;
    private String fullName;
    private String mobileNumber;
    private String email;
    private String state;
    private boolean kycStatus;
}
