package com.telecom.tsms.service;

import com.telecom.tsms.dto.LoginRequest;
import com.telecom.tsms.dto.LoginResponse;
import com.telecom.tsms.dto.RegisterRequest;
import com.telecom.tsms.dto.RegisterResponse;
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

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return LoginResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("CUSTOMER")
                .build();

        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();

    }
}
