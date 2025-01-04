package com.retonttdata.user.domain.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    Long id;
    String fullName;
    String email;
    String password;
    String phone;
}