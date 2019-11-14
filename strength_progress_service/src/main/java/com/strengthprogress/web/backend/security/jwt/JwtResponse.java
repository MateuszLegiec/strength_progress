package com.strengthprogress.web.backend.security.jwt;


import lombok.Getter;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    @Getter
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
