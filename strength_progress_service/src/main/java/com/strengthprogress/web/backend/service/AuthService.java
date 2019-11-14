package com.strengthprogress.web.backend.service;

import com.strengthprogress.web.backend.security.jwt.JwtResponse;
import com.strengthprogress.web.backend.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, @Qualifier("customUserDetailService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public JwtResponse createAuthenticationToken(String username, String password) {
        authenticate(username, password);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) throws BadCredentialsException {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
