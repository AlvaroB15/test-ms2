package com.retonttdata.user.infrastructure.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class UserEntity {
    @Id
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
}