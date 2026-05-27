package com.telecom.tsms.service;

import com.telecom.tsms.dto.LoginRequest;
import com.telecom.tsms.dto.LoginResponse;
import com.telecom.tsms.dto.RegisterRequest;
import com.telecom.tsms.dto.RegisterResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);

}
