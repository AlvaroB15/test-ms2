package com.retonttdata.user.application.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponse {
    Long id;
    String fullName;
    String email;
    String phone;
}