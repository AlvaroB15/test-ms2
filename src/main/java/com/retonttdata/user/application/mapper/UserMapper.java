package com.retonttdata.user.application.mapper;

import com.retonttdata.user.application.dto.UserRegistrationRequest;
import com.retonttdata.user.application.dto.UserResponse;
import com.retonttdata.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRegistrationRequest request) {
        return User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}