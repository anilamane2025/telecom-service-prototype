package com.telecom.tsms.service;

import com.telecom.tsms.dto.LoginRequest;
import com.telecom.tsms.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}
