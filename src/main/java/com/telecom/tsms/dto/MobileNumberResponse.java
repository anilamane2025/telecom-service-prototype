package com.telecom.tsms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class MobileNumberResponse {

    private Long id;
    private Long customerId;
    private String customerName;
    private String mobileNumber;
    private Boolean primary;
    private String status;

}
