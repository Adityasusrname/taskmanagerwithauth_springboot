package com.adityasrivastava.taskmanagerwithauth.security;

import com.adityasrivastava.taskmanagerwithauth.services.userService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

    private JWTAuthenticationFilter jwtAuthenticationFilter;
    private JWTService jwtService;
    private userService userService;

    public AppSecurityConfig(JWTService jwtService,userService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.jwtAuthenticationFilter = new JWTAuthenticationFilter(
                new JWTAuthenticationManager(jwtService, userService));
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

     http.cors().and().csrf().disable().authorizeRequests().
             antMatchers(HttpMethod.POST,"/users","/users/login").permitAll();

     http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);



    }
}
