package com.strengthprogress.web.backend.controller;

import com.strengthprogress.web.backend.dto.LoginDTO;
import com.strengthprogress.web.backend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ExceptionHandler({BadCredentialsException.class})
    public String handleException(final Exception e) {
        return e.getMessage();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(authService.createAuthenticationToken(dto.getUsername(),dto.getPassword()));
    }
}
