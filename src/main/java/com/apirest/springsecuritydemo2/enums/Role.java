package com.apirest.springsecuritydemo2.enums;

import lombok.Getter;

@Getter
public enum Role {
    
    ADMIN("admin"),
    USER("user");

    private String role;

    private Role(String role) {
        this.role = role;
    }

}
