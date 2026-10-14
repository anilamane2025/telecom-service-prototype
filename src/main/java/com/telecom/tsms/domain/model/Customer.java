package com.telecom.tsms.domain.model;

import com.telecom.tsms.enums.KycStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    private Long id;
    private String customerCode;
    private String fullName;
    private String email;
    private String state;
    private KycStatus kycStatus;

}