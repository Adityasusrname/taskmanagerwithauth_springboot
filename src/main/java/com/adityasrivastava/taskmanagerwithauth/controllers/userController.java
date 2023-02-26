package com.adityasrivastava.taskmanagerwithauth.controllers;

import com.adityasrivastava.taskmanagerwithauth.daos.createUserRequest;
import com.adityasrivastava.taskmanagerwithauth.daos.userResponse;
import com.adityasrivastava.taskmanagerwithauth.entities.userEntity;
import com.adityasrivastava.taskmanagerwithauth.security.JWTService;
import com.adityasrivastava.taskmanagerwithauth.services.userService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class userController {
    private final userService userService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;


    public userController(userService userService, ModelMapper modelMapper, JWTService jwtService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
    }

    @PostMapping("")
    ResponseEntity<userResponse> createUser(@RequestBody createUserRequest request){
        System.out.println(request.getPassword().toString());
        userEntity savedUser = userService.createUser(request);
        URI savedUserUri = URI.create("/users/"+savedUser.getId());
        var userResponse = modelMapper.map(savedUser,userResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.getId()));
        return ResponseEntity.created(savedUserUri).body(userResponse);
    }


}
