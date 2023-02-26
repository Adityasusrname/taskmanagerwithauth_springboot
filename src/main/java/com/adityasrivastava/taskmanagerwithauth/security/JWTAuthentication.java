package com.adityasrivastava.taskmanagerwithauth.security;


import com.adityasrivastava.taskmanagerwithauth.entities.userEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthentication implements Authentication {

    String jwt;
    userEntity userEntity;

    public JWTAuthentication(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwt;
    }


    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public userEntity getPrincipal() {
        return userEntity;
    }

    @Override
    public boolean isAuthenticated() {
     return(userEntity!=null);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
