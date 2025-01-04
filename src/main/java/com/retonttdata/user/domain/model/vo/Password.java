package com.retonttdata.user.domain.model.vo;

import lombok.Value;

@Value
public class Password {
    String value;

    public Password(String value) {
        if (!value.matches("^(?=.*[A-Z])(?=.*[0-9]).{8,}$")) {
            throw new IllegalArgumentException("Password must be at least 8 characters, contain one uppercase letter and one number");
        }
        this.value = value;
    }
}