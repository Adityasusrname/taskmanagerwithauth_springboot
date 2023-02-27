package com.adityasrivastava.taskmanagerwithauth.services;

import com.adityasrivastava.taskmanagerwithauth.daos.createUserRequest;
import com.adityasrivastava.taskmanagerwithauth.repositories.userRepository;
import com.adityasrivastava.taskmanagerwithauth.entities.userEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService {
    private final userRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public userService(com.adityasrivastava.taskmanagerwithauth.repositories.userRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public userEntity createUser(createUserRequest u){

        userEntity newUser = modelMapper.map(u,userEntity.class);
        newUser.setPassword(passwordEncoder.encode(u.getPassword()));

        return userRepository.save(newUser);

    }

    public userEntity getUser(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }

    public userEntity getUser(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public userEntity loginUser(String username,String password){
        var user = userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        var passMatch = passwordEncoder.matches(password,user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException();
        return user;

    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username){
            super("User with username: " + username + " not found");
        }

        public UserNotFoundException(Long userId){super("User with userId: "+ userId + " not found");}

    }

    public static class InvalidCredentialsException extends IllegalArgumentException {
        public InvalidCredentialsException() {
            super("Invalid username or password combination");
        }
    }

}
