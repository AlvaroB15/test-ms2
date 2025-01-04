package com.retonttdata.user.domain.model.vo;

import lombok.Value;

@Value
public class Email {
    String value;

    public Email(String value) {
        if (!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }
}