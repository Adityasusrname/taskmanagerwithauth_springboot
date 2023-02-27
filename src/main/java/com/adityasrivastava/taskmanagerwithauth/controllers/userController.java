package com.adityasrivastava.taskmanagerwithauth.controllers;

import com.adityasrivastava.taskmanagerwithauth.dtos.ErrorResponse;
import com.adityasrivastava.taskmanagerwithauth.dtos.createUserRequest;
import com.adityasrivastava.taskmanagerwithauth.dtos.loginUserRequest;
import com.adityasrivastava.taskmanagerwithauth.dtos.userResponse;
import com.adityasrivastava.taskmanagerwithauth.entities.userEntity;
import com.adityasrivastava.taskmanagerwithauth.security.JWTService;
import com.adityasrivastava.taskmanagerwithauth.services.userService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        userEntity savedUser = userService.createUser(request);
        URI savedUserUri = URI.create("/users/"+savedUser.getId());
        var userResponse = modelMapper.map(savedUser,userResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.getId()));
        return ResponseEntity.created(savedUserUri).body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<userResponse> loginUser(@RequestBody loginUserRequest request){
        userEntity savedUser = userService.loginUser(request.getUsername(),request.getPassword());
        var userResponse = modelMapper.map(savedUser,userResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.getId()));
        return ResponseEntity.ok(userResponse);
    }


    @ExceptionHandler({
            userService.UserNotFoundException.class,
            userService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse> handleUserExceptions(Exception ex){
         String message;
        HttpStatus status;
        if(ex instanceof userService.UserNotFoundException){
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else if(ex instanceof userService.InvalidCredentialsException){
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        }
        else{
            message = "Something went wrong!";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response = ErrorResponse.builder().message(message).build();

        return ResponseEntity.status(status).body(response);

    }

}
