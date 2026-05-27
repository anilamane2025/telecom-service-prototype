package com.telecom.tsms.service;

import com.telecom.tsms.dto.UserResponse;
import com.telecom.tsms.entity.User;
import com.telecom.tsms.exception.ResourceNotFoundException;
import com.telecom.tsms.repository.UserRepository;
import com.telecom.tsms.security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserResponse getCurrentUserProfile() {

        String username = SecurityUtils.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(()->
                        new ResourceNotFoundException("User not found with username: "+username)
                        );

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
