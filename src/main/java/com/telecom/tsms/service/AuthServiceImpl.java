package com.telecom.tsms.service;

import com.telecom.tsms.dto.LoginRequest;
import com.telecom.tsms.dto.LoginResponse;
import com.telecom.tsms.entity.User;
import com.telecom.tsms.repository.UserRepository;
import com.telecom.tsms.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil){

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername()
        ).orElseThrow(()->new RuntimeException("Invalid username or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(), user.getPassword()
        );

        if(!passwordMatches){
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }
}
