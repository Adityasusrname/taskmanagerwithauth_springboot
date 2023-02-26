package com.adityasrivastava.taskmanagerwithauth.security;

import com.adityasrivastava.taskmanagerwithauth.services.userService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {

    private JWTService jwtService;
    private userService userService;

    public JWTAuthenticationManager(JWTService jwtService, com.adityasrivastava.taskmanagerwithauth.services.userService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication){
            var jwtAuthentication = (JWTAuthentication)authentication;
            var jwt = jwtAuthentication.getCredentials();
            var userId = jwtService.retrieveUserId(jwt);
            var userEntity = userService.getUser(userId);
            jwtAuthentication.userEntity = userEntity;
            jwtAuthentication.setAuthenticated(true);
            return jwtAuthentication;

        }
        return null;
    }
}
