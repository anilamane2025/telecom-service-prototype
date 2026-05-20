package com.telecom.tsms.controller;

import com.telecom.tsms.dto.LoginRequest;
import com.telecom.tsms.dto.LoginResponse;
import com.telecom.tsms.security.JwtUtil;
import com.telecom.tsms.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    //private final JwtUtil jwtUtil;

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    /*public AuthController(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }*/

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){

        /*if("admin".equals(request.getUsername())
                && "admin123".equals(request.getPassword())){

            String token = jwtUtil.generateToken(request.getUsername());

            LoginResponse response = LoginResponse.builder()
                    .token(token)
                    .tokenType("Bearer")
                    .build();

            return ResponseEntity.ok(response);
        }*/
        //return ResponseEntity.status(401).build();
        return ResponseEntity.ok(authService.login(request));
    }

}