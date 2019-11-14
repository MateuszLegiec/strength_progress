package com.strengthprogress.web.backend.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public enum Role implements GrantedAuthority {

    ROLE_USER("ROLE_USER");

    @Id
    String name;

    Role(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
