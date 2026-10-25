package com.telecom.tsms.adapter.out;

import com.telecom.tsms.domain.model.MobileNumber;
import com.telecom.tsms.port.out.MobileNumberPort;
import org.springframework.stereotype.Component;

@Component
public class MobileNumberPersistenceAdapter implements MobileNumberPort {

    @Override
    public MobileNumber save(MobileNumber mobileNumber) {
        return null;
    }
}
