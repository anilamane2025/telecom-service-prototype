package com.telecom.tsms.controller;

import com.telecom.tsms.dto.UserResponse;
import com.telecom.tsms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(){
        return ResponseEntity.ok(
            userService.getCurrentUserProfile()
        );
    }

}
