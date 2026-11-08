package com.telecom.tsms.port.out;

import com.telecom.tsms.domain.model.MobileNumber;

import java.util.Optional;

public interface MobileNumberPort {

    MobileNumber save(MobileNumber mobileNumber);
    Optional<MobileNumber> findByMobileNumber(String mobileNumber);

}
